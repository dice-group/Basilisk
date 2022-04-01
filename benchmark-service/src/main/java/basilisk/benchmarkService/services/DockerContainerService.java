package basilisk.benchmarkService.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerContainerService {

    private final Logger logger = LoggerFactory.getLogger(DockerContainerService.class);
    private final DockerClient client;

    public DockerContainerService(DockerClient client) {
        this.client = client;
    }

    public String pullImage(String owner, String name, String tag) {

        String imageName = getImageName(owner, name, tag);

        this.logger.info("started..");
        try {
            this.client.pullImageCmd(imageName).exec(new PullImageResultCallback()).awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.logger.info("finished");

        List<Image> images = this.client.listImagesCmd().withImageNameFilter(imageName).exec();
        if (images.size() == 1) {
            return images.get(0).getId();
        } else {
            return "unexpected image count: " + images;
        }
    }

    public String startContainer(String owner, String name, String tag) {

        CreateContainerResponse response = this.client.createContainerCmd(getImageName(owner, name, tag)).exec();

        this.logger.info("created container: {}", response);

        this.client.startContainerCmd(response.getId()).exec();

        return response.getId();
    }

    public void stopContainer(String id) {

        this.client.stopContainerCmd(id).exec();

        this.client.removeContainerCmd(id).exec();

    }

    public void deleteImage(String id) {
        this.client.removeImageCmd(id).exec();
    }

    public List<Image> listImages() {
        return this.client.listImagesCmd().exec();
    }

    public String findImageIdByName(String owner, String name, String tag) {
        try {
            InspectImageResponse response = this.client.inspectImageCmd(getImageName(owner, name, tag)).exec();
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
