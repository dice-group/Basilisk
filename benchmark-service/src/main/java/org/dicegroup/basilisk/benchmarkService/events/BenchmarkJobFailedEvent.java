package org.dicegroup.basilisk.benchmarkService.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fakhr Shaheen
 */
@Getter
@Setter
@AllArgsConstructor
public class BenchmarkJobFailedEvent {

    Long jobId;

}
