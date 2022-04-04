package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.dockerContainer.ContainerStatus;
import basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import basilisk.benchmarkService.domain.dockerContainer.ImageStatus;
import basilisk.benchmarkService.repositories.DockerContainerRepository;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.PortBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DockerContainerService {

    private final Logger logger = LoggerFactory.getLogger(DockerContainerService.class);
    private final DockerClient client;
    private final DockerContainerRepository containerRepository;

    public DockerContainerService(DockerClient client, DockerContainerRepository containerRepository) {
        this.client = client;
        this.containerRepository = containerRepository;
    }

    public DockerContainer addContainer(String owner, String name, String tag) {
        String imageName = getImageName(owner, name, tag);

        Optional<DockerContainer> containerOpt = this.containerRepository.findByImageName(imageName);

        if (containerOpt.isPresent()) {
            return containerOpt.get();
        }

        DockerContainer container = DockerContainer.builder()
                .imageName(getImageName(owner, name, tag))
                .imageStatus(ImageStatus.NOT_PULLED)
                .containerStatus(ContainerStatus.NOT_CREATED)
                .build();
        return this.containerRepository.save(container);
    }

    public String pullImage(DockerContainer container) {

        this.logger.info("started pulling..");
        try {
            this.client.pullImageCmd(container.getImageName())
                    .exec(new PullImageResultCallback())
                    .awaitCompletion();

            container.setImageStatus(ImageStatus.PULLED);
            container.setImageId(findImageIdByName(container.getImageName()));

            container = this.containerRepository.save(container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.logger.info("finished");

        return container.getImageId();
    }

    public String startContainer(DockerContainer container) {

        HostConfig config = HostConfig.newHostConfig().withPortBindings(PortBinding.parse("81:80"));

        CreateContainerResponse response = this.client.createContainerCmd(container.getImageName())
                .withHostConfig(config)
                .exec();

        container.setContainerStatus(ContainerStatus.CREATED);
        container.setContainerId(response.getId());
        container = this.containerRepository.save(container);

        this.logger.info("created container: {}", response);

        this.client.startContainerCmd(container.getContainerId()).exec();

        container.setContainerStatus(ContainerStatus.RUNNING);
        container = this.containerRepository.save(container);


        return container.getContainerId();
    }

    public void stopContainer(DockerContainer container) {

        this.client.stopContainerCmd(container.getContainerId()).exec();

        container.setContainerStatus(ContainerStatus.STOPPED);
        container = this.containerRepository.save(container);

        this.client.removeContainerCmd(container.getContainerId()).exec();

        container.setContainerStatus(ContainerStatus.REMOVED);
        container.setContainerId(null);
        this.containerRepository.save(container);

    }

    public void deleteImage(DockerContainer container) {
        this.client.removeImageCmd(container.getImageId()).exec();

        container.setImageStatus(ImageStatus.REMOVED);
        container.setImageId(null);
        this.containerRepository.save(container);
    }

    public List<Image> listImages() {
        return this.client.listImagesCmd().exec();
    }

    public List<DockerContainer> getAllContainers() {
        return (List<DockerContainer>) this.containerRepository.findAll();
    }

    public Optional<DockerContainer> getDockerContainer(String owner, String name, String tag) {
        return this.containerRepository.findByImageName(getImageName(owner, name, tag));
    }

    public String findImageIdByName(String owner, String name, String tag) {
        return findImageIdByName(getImageName(owner, name, tag));
    }

    public String findImageIdByName(String imageName) {
        try {
            InspectImageResponse response = this.client.inspectImageCmd(imageName).exec();
            logger.info("response: {}", response);

            return response.getId();
        } catch (NotFoundException e) {
            logger.info("not found");
        }
        return null;
    }

    private String getImageName(String owner, String name, String tag) {
        return owner + "/" + name + ":" + tag;
    }
}
