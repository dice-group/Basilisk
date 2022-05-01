package org.dicegroup.basilisk.events.benchmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BenchmarkJobAbortedEvent {
    Long jobId;
}
