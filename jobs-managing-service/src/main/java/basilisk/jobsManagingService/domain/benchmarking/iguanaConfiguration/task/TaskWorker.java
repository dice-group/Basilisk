package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;


/**
 * @author Fakhr Shaheen
 */
public abstract class TaskWorker {

    private String className;
    private int threads;
    private String queriesFile;
    private String timeOut;
    private int fixedLatency;
    private int gaussianLatency;
}
