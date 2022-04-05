package org.dicegroup.basilisk.hooksCheckingService.web.messaging;

import org.dicegroup.basilisk.events.hooks.repo.DockerRepoAddEvent;
import org.dicegroup.basilisk.events.hooks.repo.DockerRepoDeleteEvent;
import org.dicegroup.basilisk.events.hooks.repo.GitRepoAddEvent;
import org.dicegroup.basilisk.events.hooks.repo.GitRepoDeleteEvent;
import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import org.dicegroup.basilisk.hooksCheckingService.services.HooksServices.DockerRepoService;
import org.dicegroup.basilisk.hooksCheckingService.services.HooksServices.GitRepoService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "${rabbitmq.hooks.repoQueue}")
public class MessageReceiver {

    private final GitRepoService gitRepoService;
    private final DockerRepoService dockerRepoService;
    private final ModelMapper mapper;

    public MessageReceiver(GitRepoService gitRepoService, DockerRepoService dockerRepoService, ModelMapper mapper) {
        this.gitRepoService = gitRepoService;
        this.dockerRepoService = dockerRepoService;
        this.mapper = mapper;
    }

    @RabbitHandler
    public void receiveGitRepoAddEvent(GitRepoAddEvent event) {
        GitRepo repo = this.mapper.map(event, GitRepo.class);
        this.gitRepoService.addRepo(repo);
    }

    @RabbitHandler
    public void receiveGitRepoDeleteEvent(GitRepoDeleteEvent event) {
        Long id = event.getId();
        this.gitRepoService.deleteRepo(id);
    }

    @RabbitHandler
    public void receiveDockerRepoAddedEvent(DockerRepoAddEvent event) {
        DockerRepo repo = this.mapper.map(event, DockerRepo.class);
        this.dockerRepoService.addRepo(repo);
    }

    @RabbitHandler
    public void receiveDockerRepoDeleteEvent(DockerRepoDeleteEvent event) {
        Long id = event.getId();
        this.dockerRepoService.deleteRepo(id);
    }
}
