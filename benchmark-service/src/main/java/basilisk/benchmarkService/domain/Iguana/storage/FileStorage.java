package basilisk.benchmarkService.domain.Iguana.storage;

import org.springframework.beans.factory.annotation.Value;


/**
 * @author Fakhr Shaheen
 */
public class FileStorage extends Storage{

     @Value( "${IguanaConfiguration.ClassName.FileStorage}" )
     private  String className;

}
