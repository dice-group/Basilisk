package org.dicegroup.basilisk.benchmarkService.domain.iguana.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileStorageConfiguration extends StorageConfiguration {

    private String fileName;

}
