package basilisk.benchmarkService.domain.Iguana;


import basilisk.benchmarkService.domain.Iguana.storage.Storage;
import basilisk.benchmarkService.domain.Iguana.task.IguanaTask;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
@Setter
@Getter
@Builder
public class IguanaConfiguration  {

    private IguanaConnection iguanaConnection;
    private Dataset dataset;
    private Storage storage;
    private List<IguanaTask> iguanaTasks;
    private List<String> iguanaMetrics;
}
