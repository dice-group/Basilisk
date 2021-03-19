package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class OneLineTextQueryhandler extends TaskQueryHandler{

    @Autowired
    private Environment env;

    public OneLineTextQueryhandler() {
        this.className=env.getProperty("IguanaConfiguration.ClassName.InstancesQueryHandler");

    }
}
