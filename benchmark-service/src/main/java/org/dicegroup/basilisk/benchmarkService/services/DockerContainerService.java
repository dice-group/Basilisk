package org.dicegroup.basilisk.benchmarkService.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.ContainerStatus;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.ImageStatus;
import org.dicegroup.basilisk.benchmarkService.repositories.DockerContainerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DockerContainerService {

    private final DockerClient client;
    private final DockerContainerRepository containerRepository;

    @Value("${docker.containerName}")
    private String containerName;

    @Value("${docker.networkName}")
    private String networkName;

    @Value("${docker.localhost:#{false}}")
    private boolean isLocalhost;

    public DockerContainerService(DockerClient client, DockerContainerRepository containerRepository) {
        this.client = client;
        this.containerRepository = containerRepository;
    }

    public DockerContainer getDockerContainer(String owner, String name, String tag) {
        String imageName = getImageName(owner, name, tag);

        Optional<DockerContainer> containerOpt = this.containerRepository.findByImageName(imageName);

        if (containerOpt.isPresent()) {
            return containerOpt.get();
        }

        DockerContainer container = DockerContainer.builder()
                .imageName(imageName)
                .imageStatus(ImageStatus.NOT_PULLED)
                .containerStatus(ContainerStatus.NOT_CREATED)
                .build();
        return this.containerRepository.save(container);
    }

    public DockerContainer pullImage(DockerContainer container) {

        log.info("started pulling..");
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

        log.info("finished");

        return container;
    }

    public DockerContainer startContainer(DockerContainer container) {

        Volume dataSet = new Volume(container.getDataSetPath());
        Bind dataSetBind = new Bind(container.getDataSetHostPath(), dataSet);

        PortBinding portBinding = new PortBinding(Ports.Binding.bindPort(container.getHostPort()), new ExposedPort(container.getExposedPort()));

        HostConfig config = HostConfig.newHostConfig()
                .withPortBindings(portBinding)
                .withNetworkMode(getNetworkId())
                .withBinds(dataSetBind);

        CreateContainerResponse response = this.client.createContainerCmd(container.getImageName())
                .withHostConfig(config)
                .withVolumes(dataSet)
                .withName(this.containerName)
                .withAliases(this.containerName)
                .withCmd(container.getEntryPoint().split("\s"))
                .withExposedPorts(new ExposedPort(container.getExposedPort()))
                .exec();

        container.setContainerStatus(ContainerStatus.CREATED);
        container.setContainerId(response.getId());
        container = this.containerRepository.save(container);

        log.info("created container: {}", response);

        this.client.startContainerCmd(container.getContainerId()).exec();

        container.setContainerStatus(ContainerStatus.RUNNING);
        container.setContainerName(getContainerName(container.getContainerId()));

        container = this.containerRepository.save(container);


        return container;
    }

    public DockerContainer stopContainer(DockerContainer container) {

        this.client.stopContainerCmd(container.getContainerId()).exec();

        container.setContainerStatus(ContainerStatus.STOPPED);
        container = this.containerRepository.save(container);

        this.client.removeContainerCmd(container.getContainerId()).exec();

        container.setContainerStatus(ContainerStatus.REMOVED);
        container.setContainerId(null);
        return this.containerRepository.save(container);
    }

    public DockerContainer deleteImage(DockerContainer container) {
        this.client.removeImageCmd(container.getImageId()).exec();

        container.setImageStatus(ImageStatus.REMOVED);
        container.setImageId(null);
        return this.containerRepository.save(container);
    }

    public String findImageIdByName(String imageName) {
        try {
            InspectImageResponse response = this.client.inspectImageCmd(imageName).exec();
            log.info("response: {}", response);

            return response.getId();
        } catch (NotFoundException e) {
            log.info("not found");
        }
        return null;
    }

    private String getImageName(String owner, String name, String tag) {
        return owner + "/" + name + ":" + tag;
    }

    public String getContainerName(String containerId) {
        return this.client.inspectContainerCmd(containerId).exec().getName().substring(1);
    }

    public String getNetworkId() {
        for (Network network : this.client.listNetworksCmd().exec()) {
            if (network.getName().equals(this.networkName)) {
                return network.getId();
            }
        }
        return null;
    }

    public boolean isLocalhost() {
        return isLocalhost;
    }

}
