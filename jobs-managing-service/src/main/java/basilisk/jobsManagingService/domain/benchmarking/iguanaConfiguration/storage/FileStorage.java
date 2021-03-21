package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class FileStorage extends Storage{

     @Autowired
     private Environment env;

     public FileStorage() {
          this.className=env.getProperty("IguanaConfiguration.ClassName.FileStorage");
     }
}
