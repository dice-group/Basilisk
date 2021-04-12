package basilisk.benchmarkService.builder.iguana.worker;


import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Fakhr Shaheen
 */
public abstract class TaskWorkerBuilder {

    protected int threads;
    protected String queriesFile;

    @Value("${IguanaConfiguration.DefaultValues.Worker.TimeOut}")
    protected int timeOut;
    @Value("${IguanaConfiguration.DefaultValues.Worker.FixedLatency}")
    protected int fixedLatency;
    @Value("${IguanaConfiguration.DefaultValues.Worker.GaussianLatency}")
    protected int gaussianLatency;


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
