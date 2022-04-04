package org.dicegroup.basilisk.jobsManagingService.events.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.DockerBenchmarkJob;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;


@Builder
@Getter
public class DockerBenchmarkJobCreatedEvent implements Serializable {

    private DockerBenchmarkJob benchmarkJob;
    private LocalDate createdDate;

}
