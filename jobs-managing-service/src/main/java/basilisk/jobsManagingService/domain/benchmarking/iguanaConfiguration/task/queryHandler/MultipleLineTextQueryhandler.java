package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryhandler extends IguanaTaskQueryHandler {

    private String delim;

    public MultipleLineTextQueryhandler(String className, String delim) {
        super(className);
        this.delim = delim;
    }
}
