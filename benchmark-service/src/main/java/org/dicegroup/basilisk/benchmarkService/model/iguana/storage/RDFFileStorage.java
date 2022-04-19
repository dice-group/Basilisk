package org.dicegroup.basilisk.benchmarkService.model.iguana.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RDFFileStorage extends Storage {

    private final String className = "RDFFileStorage";

    @Setter
    private FileStorageConfiguration configuration;

}
