package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class MultipleLineTextQueryhandler extends TaskQueryHandler{

    private String delim;

    public MultipleLineTextQueryhandler(String className, String delim) {
        super(className);
        this.delim = delim;
    }
}
