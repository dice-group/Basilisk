package basilisk.jobsManagingService.web.messaging.benchmark;

import basilisk.jobsManagingService.events.BenchmarkJob.*;


public interface BenchmarkMessageReceiver {

    public void receive(BenchmarkJobStartedEvent benchmarkJobStartedEvent);

}
