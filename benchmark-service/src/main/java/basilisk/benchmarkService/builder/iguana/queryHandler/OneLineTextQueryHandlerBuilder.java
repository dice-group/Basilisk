package basilisk.benchmarkService.builder.iguana.queryHandler;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class OneLineTextQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {

    @Value("${IguanaConfiguration.ClassName.InstancesQueryHandler}")
    private String className;

    @Override
    public IguanaTaskQueryHandler build() {
        return new IguanaTaskQueryHandler(className);
    }
}
