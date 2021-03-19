package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryhandler extends OneLineTextQueryhandler{

    private String delim;

    @Autowired
    private Environment env;

    public MultipleLineTextQueryhandler(String delim) {
        this.className=env.getProperty("IguanaConfiguration.ClassName.DelimInstancesQueryHandler");
        this.delim = delim;
    }

    public MultipleLineTextQueryhandler() {
        this.className=env.getProperty("IguanaConfiguration.ClassName.DelimInstancesQueryHandler");
        this.delim=env.getProperty("IguanaConfiguration.MutipleLineDefaultDelim");
    }
}
