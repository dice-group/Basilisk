package org.dicegroup.basilisk.benchmarkService.domain.Iguana.storage;


import javax.persistence.Entity;

@Entity
public class FileStorage extends Storage {


     public FileStorage() {
          super("NTFileStorage");
     }
}
