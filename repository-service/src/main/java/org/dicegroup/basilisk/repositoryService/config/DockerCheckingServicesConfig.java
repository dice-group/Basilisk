package org.dicegroup.basilisk.repositoryService.config;

import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerImageRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerRepoRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerTagRepository;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.DockerhubCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DockerCheckingServicesConfig {

    @Bean
    public DockerhubCheckingService dockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, BenchmarkJobService benchmarkJobService) {
        return new DockerhubCheckingService(dockerRepoRepository, dockerImageRepository, dockerTagRepository, benchmarkJobService);
    }

}
