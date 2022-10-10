package org.dicegroup.basilisk.repositoryService.services.repo.checkingService;

import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.events.hook.DockerTagEvent;
import org.dicegroup.basilisk.repositoryService.core.exception.DockerhubException;
import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerImage;
import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerRepo;
import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerTag;
import org.dicegroup.basilisk.repositoryService.model.repo.docker.api.DockerApiTag;
import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerImageRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerRepoRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerTagRepository;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.dicegroup.basilisk.repositoryService.web.proxies.docker.DockerHubRestProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Slf4j
public class DockerhubCheckingService implements CheckingService {
    DockerImageRepository dockerImageRepository;
    DockerRepoRepository dockerRepoRepository;
    DockerTagRepository dockerTagRepository;

    BenchmarkJobService benchmarkJobService;

    @Autowired
    DockerHubRestProxy dockerHubRestProxy;

    public DockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, BenchmarkJobService benchmarkJobService) {
        this.dockerImageRepository = dockerImageRepository;
        this.dockerRepoRepository = dockerRepoRepository;
        this.dockerTagRepository = dockerTagRepository;
        this.benchmarkJobService = benchmarkJobService;
    }

    @Override
    public void performChecking() {
        for (DockerRepo dockerRepo : this.dockerRepoRepository.findAll()) {
            try {
                checkRepo(dockerRepo);
            } catch (DockerhubException e) {
                log.error("DockerhubException while checking");
            }
        }
    }


    private void checkRepo(DockerRepo dockerRepo) throws DockerhubException {
        List<DockerApiTag> retrievedTags = this.dockerHubRestProxy.getTags(dockerRepo.getRepoOwner(), dockerRepo.getRepoName());
        for (DockerApiTag apiTag : retrievedTags) {
            DockerImage dockerImage = getDockerImage(apiTag, dockerRepo);
            processDockerTag(dockerRepo, apiTag, dockerImage);
        }
    }


    private DockerImage getDockerImage(DockerApiTag apiTag, DockerRepo dockerRepo) {
        String digest = apiTag.getImages().get(0).getDigest();
        Optional<DockerImage> imageOpt = this.dockerImageRepository.findByDockerRepoAndDigest(dockerRepo, digest);

        if (imageOpt.isPresent()) {
            return imageOpt.get();
        }

        log.info("Docker Image {} of repo {} not found in database! Adding Image..", apiTag.getName(), dockerRepo.getRepoName());
        DockerImage dockerImage = DockerImage.builder()
                .digest(digest)
                .dockerRepo(dockerRepo)
                .build();

        return this.dockerImageRepository.save(dockerImage);
    }

    private void processDockerTag(DockerRepo repo, DockerApiTag apiTag, DockerImage dockerImage) {
        Optional<DockerTag> tagOpt = this.dockerTagRepository.findDockerTagByDockerRepoAndName(repo, apiTag.getName());

        if (tagOpt.isEmpty()) {
            //Add the tag to the database
            DockerTag newTag = DockerTag.builder()
                    .name(apiTag.getName())
                    .dockerRepo(repo)
                    .dockerImage(dockerImage)
                    .build();

            this.dockerTagRepository.save(newTag);
            // send docker tag added event
            DockerTagEvent event = DockerTagEvent.builder()
                    .repoId(repo.getId())
                    .imageDigest(dockerImage.getDigest())
                    .tagName(apiTag.getName())
                    .build();

            this.benchmarkJobService.generateBenchmarkJobs(event);

        } else {
            DockerTag tag = tagOpt.get();

            if (!tag.getDockerImage().getId().equals(dockerImage.getId())) {
                tag.setDockerImage(dockerImage);
                tag = this.dockerTagRepository.save(tag);

                DockerTagEvent updatedEvent = DockerTagEvent.builder()
                        .repoId(repo.getId())
                        .tagName(tag.getName())
                        .imageDigest(dockerImage.getDigest())
                        .build();

                this.benchmarkJobService.generateBenchmarkJobs(updatedEvent);

            }
        }
    }
}
