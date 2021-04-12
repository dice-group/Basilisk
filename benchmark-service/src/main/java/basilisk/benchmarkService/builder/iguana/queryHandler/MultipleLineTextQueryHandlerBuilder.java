package basilisk.benchmarkService.builder.iguana.queryHandler;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.benchmarkService.domain.Iguana.task.queryHandler.MultipleLineTextQueryhandler;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {

    @Value("${IguanaConfiguration.MutipleLineDefaultDelim}")
    private String delim;

    @Value("${IguanaConfiguration.ClassName.DelimInstancesQueryHandler}")
    private String className;


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
