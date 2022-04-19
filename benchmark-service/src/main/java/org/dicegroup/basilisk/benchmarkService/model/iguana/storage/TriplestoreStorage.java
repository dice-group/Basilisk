package org.dicegroup.basilisk.benchmarkService.model.iguana.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TriplestoreStorage extends Storage {

    private final String className = "TriplestoreStorage";

    @Setter
    private TriplestoreConfiguration configuration;

}
