package org.dicegroup.basilisk.benchmarkService.web.controllers;

import com.github.dockerjava.api.model.Image;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.services.DockerContainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    private final DockerContainerService containerService;

    private final String owner = "dicegroup"; // "docker"
    private final String name = "tentris_server"; // "getting-started"
    private final String tag = "latest";
    private final String startArgs = "-f /datasets/swdf.nt";

    public TestController(DockerContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping("/add")
    public DockerContainer addContainer() {
        return this.containerService.getDockerContainer(this.owner, this.name, this.tag);
    }

    @GetMapping("/containers")
    public List<DockerContainer> getContainers() {
        return this.containerService.getAllContainers();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Image>> listContainers() {
        List<Image> images = this.containerService.listImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/find")
    public String findImageID() {
        return this.containerService.findImageIdByName(this.owner, this.name, this.tag);
    }

    @GetMapping("/pull")
    public String pullImage() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        return this.containerService.pullImage(container).getImageId();
    }

    @GetMapping("/start")
    public String startContainer() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        String id = this.containerService.startContainer(container).getContainerId();
        return "started.. with ID: " + id;
    }

    @GetMapping("/stop")
    public String stopContainer() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        this.containerService.stopContainer(container);
        return "stopped";
    }

    @GetMapping("/delete")
    public String deleteImage() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        this.containerService.deleteImage(container);
        return "deleted";
    }
}
