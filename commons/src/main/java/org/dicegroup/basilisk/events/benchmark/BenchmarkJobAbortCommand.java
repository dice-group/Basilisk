package org.dicegroup.basilisk.events.benchmark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BenchmarkJobAbortCommand implements Serializable {

    private Long benchmarkJobId;

}
