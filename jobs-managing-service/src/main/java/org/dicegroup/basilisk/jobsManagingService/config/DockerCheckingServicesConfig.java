package org.dicegroup.basilisk.jobsManagingService.config;

import org.dicegroup.basilisk.jobsManagingService.repositories.repo.DockerImageRepository;
import org.dicegroup.basilisk.jobsManagingService.repositories.repo.DockerRepoRepository;
import org.dicegroup.basilisk.jobsManagingService.repositories.repo.DockerTagRepository;
import org.dicegroup.basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.dicegroup.basilisk.jobsManagingService.services.repo.checkingService.DockerhubCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DockerCheckingServicesConfig {

    @Bean
    public DockerhubCheckingService dockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, BenchmarkJobService benchmarkJobService) {
        return new DockerhubCheckingService(dockerRepoRepository, dockerImageRepository, dockerTagRepository, benchmarkJobService);
    }

}
