package org.dicegroup.basilisk.benchmarkService.web.controllers;

import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.services.DockerContainerService;
import com.github.dockerjava.api.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("test")
public class TestController {

    private final DockerContainerService containerService;
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final String owner = "docker";
    private final String name = "getting-started";
    private final String tag = "latest";

    public TestController(DockerContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping("/add")
    public DockerContainer addContainer() {
        return this.containerService.addContainer(this.owner, this.name, this.tag);
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
        Optional<DockerContainer> containerOpt = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        if (containerOpt.isPresent()) {
            return this.containerService.pullImage(containerOpt.get());
        }

        return "container not found";
    }

    @GetMapping("/start")
    public String startContainer() {
        Optional<DockerContainer> containerOpt = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        if (containerOpt.isPresent()) {
            String id = this.containerService.startContainer(containerOpt.get());

            return "started.. with ID: " + id;
        }

        return "something went wrong";
    }

    @GetMapping("/stop")
    public String stopContainer() {
        Optional<DockerContainer> container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        if (container.isPresent()) {
            this.containerService.stopContainer(container.get());
            return "stopped";
        }
        return "something went wrong";
    }

    @GetMapping("/delete")
    public String deleteImage() {
        Optional<DockerContainer> container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        if (container.isPresent()) {
            this.containerService.deleteImage(container.get());
            return "deleted";
        }
        return "something went wrong";
    }
}
