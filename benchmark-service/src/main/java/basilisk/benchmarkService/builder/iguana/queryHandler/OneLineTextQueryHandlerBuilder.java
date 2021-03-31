package basilisk.benchmarkService.builder.iguana.queryHandler;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class OneLineTextQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {

    @Autowired
    private Environment env;

    public OneLineTextQueryHandlerBuilder() {
        this.className=env.getProperty("IguanaConfiguration.ClassName.InstancesQueryHandler");
    }

    @Override
    public IguanaTaskQueryHandler build() {
        return new IguanaTaskQueryHandler(className);
    }
}
