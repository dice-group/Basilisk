package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker;

/**
 * @author Fakhr Shaheen
 */
public abstract class CliTaskWorker extends TaskWorker{


    public CliTaskWorker(int threads, String queriesFile, int timeOut, int fixedLatency, int gaussianLatency, String classname) {
        super(threads, queriesFile, timeOut, fixedLatency, gaussianLatency, classname);
    }
}
