package basilisk.benchmarkService.builder.iguana.queryHandler;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.benchmarkService.domain.Iguana.task.queryHandler.MultipleLineTextQueryhandler;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {


    private String delim="\n";

    private final String className="DelimInstancesQueryHandler";


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
