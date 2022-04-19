package org.dicegroup.basilisk.benchmarkService.model.iguana.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NTFileStorage extends Storage {

    private final String className = "NTFileStorage";

    @Setter
    private FileStorageConfiguration configuration;

}
