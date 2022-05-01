package org.dicegroup.basilisk.events.benchmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BenchmarkJobStartedEvent {
    Long jobId;
}
