package org.dicegroup.basilisk.hooksCheckingService.web.messaging;

import org.dicegroup.basilisk.hooksCheckingService.events.RepoEventType;
import org.dicegroup.basilisk.hooksCheckingService.events.docker.DockerRepoEvent;
import org.dicegroup.basilisk.hooksCheckingService.events.git.GitRepoEvent;
import org.dicegroup.basilisk.hooksCheckingService.services.HooksServices.DockerRepoService;
import org.dicegroup.basilisk.hooksCheckingService.services.HooksServices.GitRepoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageReceiver {

    private final DockerRepoService dockerRepoService;
    private final GitRepoService gitRepoService;

    public MessageReceiver(DockerRepoService dockerRepoService, GitRepoService gitRepoService) {
        this.dockerRepoService = dockerRepoService;
        this.gitRepoService = gitRepoService;
    }


    @RabbitListener(queues = "${rabbitmq.hooks.docker.repoQueue}")
    public void receiveDockerRepoEvent(DockerRepoEvent event) {
        if (event.getEventType() == RepoEventType.CREATED) {
            this.dockerRepoService.addRepo(event.getRepo());
        } else {
            this.dockerRepoService.deleteRepo(event.getRepo());
        }
    }

    @RabbitListener(queues = "${rabbitmq.hooks.git.repoQueue}")
    public void receiveGitRepoEvent(GitRepoEvent event) {
        if (event.getEventType() == RepoEventType.CREATED) {
            this.gitRepoService.addRepo(event.getRepo());
        } else {
            this.gitRepoService.deleteRepo(event.getRepo());
        }
    }
}
