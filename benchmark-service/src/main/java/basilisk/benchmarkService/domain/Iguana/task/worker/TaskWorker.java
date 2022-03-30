package basilisk.benchmarkService.domain.Iguana.task.worker;


import basilisk.benchmarkService.domain.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
public abstract class TaskWorker extends BaseEntity {

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
