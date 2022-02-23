package basilisk.hooksCheckingService.web.messaging;

import basilisk.hooksCheckingService.events.docker.DockerRepoAddedEvent;
import basilisk.hooksCheckingService.events.docker.DockerRepoDeletedEvent;
import basilisk.hooksCheckingService.events.git.GitRepoAddedEvent;
import basilisk.hooksCheckingService.events.git.GitRepoDeletedEvent;
import basilisk.hooksCheckingService.services.HooksServices.DockerRepoService;
import basilisk.hooksCheckingService.services.HooksServices.GitRepoService;
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


    @RabbitListener(queues = "${rabbitmq.hooks.docker.repoAddedQueue}")
    public void receiveDockerRepoAddedEvent(DockerRepoAddedEvent event) {
        this.dockerRepoService.addDockerRepo(event.getRepo());
    }

    @RabbitListener(queues = "${rabbitmq.hooks.docker.repoDeletedQueue}")
    public void receiveDockerRepoDeletedEvent(DockerRepoDeletedEvent event) {
        this.dockerRepoService.deleteRepo(event.getRepo());
    }

    @RabbitListener(queues = "${rabbitmq.hooks.git.repoAddedQueue}")
    public void receiveGitRepoAddedEvent(GitRepoAddedEvent event) {
        this.gitRepoService.addRepo(event.getRepo());
    }

    @RabbitListener(queues = "${rabbitmq.hooks.git.repoDeletedQueue}")
    public void receiveGitRepoDeletedEvent(GitRepoDeletedEvent event) {
        this.gitRepoService.deleteRepo(event.getRepo());
    }
}
