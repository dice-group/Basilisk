package basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.MultipleLineTextQueryhandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.IguanaTaskQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {

    @Value("${IguanaConfiguration.MutipleLineDefaultDelim}")
    private String delim;

    @Autowired
    private Environment env;

    public MultipleLineTextQueryHandlerBuilder() {
        this.className=env.getProperty("IguanaConfiguration.ClassName.DelimInstancesQueryHandler");
    }

    public MultipleLineTextQueryHandlerBuilder setDelim(String delim)
    {
        this.delim=delim;
        return this;
    }

    @Override
    public IguanaTaskQueryHandler build() {
        return new MultipleLineTextQueryhandler(className,delim);
    }
}
