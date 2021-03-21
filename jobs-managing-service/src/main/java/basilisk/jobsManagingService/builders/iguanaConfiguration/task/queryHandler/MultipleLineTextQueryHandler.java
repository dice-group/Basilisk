package basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.MultipleLineTextQueryhandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.TaskQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryHandler extends TaskQueryHandlerBuilder{

    @Value("${IguanaConfiguration.MutipleLineDefaultDelim}")
    private String delim;

    @Autowired
    private Environment env;

    public MultipleLineTextQueryHandler() {
        this.className=env.getProperty("IguanaConfiguration.ClassName.DelimInstancesQueryHandler");
    }

    public MultipleLineTextQueryHandler setDelim(String delim)
    {
        this.delim=delim;
        return this;
    }

    @Override
    public TaskQueryHandler build() {
        return new MultipleLineTextQueryhandler(className,delim);
    }
}
