package basilisk.jobsManagingService.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQJobsConfig {
    @Value("${jobs.benchmark.rabbitmq.exchange}")
    String exchange;

    @Value("${jobs.benchmark.rabbitmq.createdEventQueue}")
    String benchmarkCreatedEventQueueName;
    @Value("${jobs.benchmark.rabbitmq.createdEventRoutingkey}")
    private String benchmarkCreatedEventRoutingKey;

    @Value("${jobs.benchmark.rabbitmq.startedEventQueue}")
    String benchmarkStartedEventQueueName;
    @Value("${jobs.benchmark.rabbitmq.startedEventRoutingkey}")
    private String benchmarkStartedEventRoutingKey;

    @Value("${jobs.benchmark.rabbitmq.finishedEventQueue}")
    String benchmarkFinishedEventQueueName;
    @Value("${jobs.benchmark.rabbitmq.finishedEventRoutingkey}")
    private String benchmarkFinishedEventRoutingKey;

    @Value("${jobs.benchmark.rabbitmq.failedEventQueue}")
    String benchmarkFailedEventQueueName;
    @Value("${jobs.benchmark.rabbitmq.failedEventRoutingkey}")
    private String benchmarkFailedEventRoutingKey;

    @Value("${jobs.benchmark.rabbitmq.abortedEventQueue}")
    String benchmarkAbortedEventQueueName;
    @Value("${jobs.benchmark.rabbitmq.abortedEventRoutingkey}")
    private String benchmarkAbortedEventRoutingKey;

    @Value("${jobs.benchmark.rabbitmq.abortCommandQueue}")
    String benchmarkAbortCommandQueueName;
    @Value("${jobs.benchmark.rabbitmq.abortCommandRoutingkey}")
    private String benchmarkAbortCommandRoutingKey;

    @Bean
    Queue benchmarkCreatedEventQueue() {return new Queue(benchmarkCreatedEventQueueName, false);}

    @Bean
    Queue benchmarkStartedEventQueue() {return new Queue(benchmarkStartedEventQueueName, false);}

    @Bean
    Queue benchmarkFinishedEventQueue() {return new Queue(benchmarkFinishedEventQueueName, false);}

    @Bean
    Queue benchmarkFailedEventQueue() {return new Queue(benchmarkFailedEventQueueName, false);}

    @Bean
    Queue benchmarkAbortedEventQueue() {return new Queue(benchmarkAbortedEventQueueName, false);}

    @Bean
    Queue benchmarkAbortCommandQueue() {return new Queue(benchmarkAbortCommandQueueName, false);}
}
