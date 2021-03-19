package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;


/**
 * @author Fakhr Shaheen
 */
public abstract class TaskWorker {

    public TaskWorker(int threads, String queriesFile, String timeOut, int fixedLatency, int gaussianLatency) {
        this.threads = threads;
        this.queriesFile = queriesFile;
        this.timeOut = timeOut;
        this.fixedLatency = fixedLatency;
        this.gaussianLatency = gaussianLatency;
    }

    private int threads;
    private String queriesFile;
    private String timeOut;
    private int fixedLatency;
    private int gaussianLatency;

    protected String className;
}
