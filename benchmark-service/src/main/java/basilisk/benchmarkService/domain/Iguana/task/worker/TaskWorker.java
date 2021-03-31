package basilisk.benchmarkService.domain.Iguana.task.worker;


/**
 * @author Fakhr Shaheen
 */


public abstract class TaskWorker {

    public TaskWorker(int threads, String queriesFile, int timeOut, int fixedLatency, int gaussianLatency, String classname) {
        this.threads = threads;
        this.queriesFile = queriesFile;
        this.timeOut = timeOut;
        this.fixedLatency = fixedLatency;
        this.gaussianLatency = gaussianLatency;
        this.classname = classname;
    }

    protected int threads;
    protected String queriesFile;
    protected int timeOut;
    protected int fixedLatency;
    protected int gaussianLatency;

    protected String classname;
}
