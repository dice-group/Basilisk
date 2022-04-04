package org.dicegroup.basilisk.jobsManagingService.events.benchmarking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Fakhr Shaheen
 */
@Getter
@Setter
@AllArgsConstructor
public class BenchmarkJobFinishedEvent implements Serializable {
    Long jobId;
}
