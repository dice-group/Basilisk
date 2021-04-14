package basilisk.benchmarkService.builder.iguana.worker;


import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;

/**
 * @author Fakhr Shaheen
 */
public abstract class TaskWorkerBuilder {

    protected int threads;

    protected String queriesFile;

    protected int timeOut=180000;

    protected int fixedLatency=0;

    protected int gaussianLatency=0;


    public TaskWorkerBuilder(int threads, String queriesFile)
    {
        this.threads = threads;
        this.queriesFile = queriesFile;
    }
    public TaskWorkerBuilder setTimeOut(int timeOut)
    {
        this.timeOut=timeOut;
        return this;
    }

    public TaskWorkerBuilder setFixedLatency(int fixedLatency)
    {
        this.fixedLatency=fixedLatency;
        return this;
    }

    public TaskWorkerBuilder setGaussianLatency(int gaussianLatency)
    {
        this.gaussianLatency=gaussianLatency;
        return this;
    }


    public abstract TaskWorker build();
}
