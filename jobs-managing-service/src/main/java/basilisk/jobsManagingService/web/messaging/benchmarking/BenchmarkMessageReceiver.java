package basilisk.jobsManagingService.web.messaging.benchmarking;

import basilisk.jobsManagingService.events.benchmarking.BenchmarkJobStartedEvent;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class BenchmarkMessageReceiver {

    private final BenchmarkJobService benchmarkJobService;

    private final Logger logger = LoggerFactory.getLogger(BenchmarkMessageReceiver.class);

    public BenchmarkMessageReceiver(BenchmarkJobService benchmarkJobService) {
        this.benchmarkJobService = benchmarkJobService;
    }

    @RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
    public void receive(BenchmarkJobStartedEvent benchmarkJobStartedEvent) {
        benchmarkJobService.setJobStatusAsStarted(benchmarkJobStartedEvent.getJobId());
    }
}
