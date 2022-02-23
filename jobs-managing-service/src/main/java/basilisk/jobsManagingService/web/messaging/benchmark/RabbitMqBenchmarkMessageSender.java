package basilisk.jobsManagingService.web.messaging.benchmark;

import basilisk.jobsManagingService.core.exception.MessageSendingExecption;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;
import basilisk.jobsManagingService.web.messaging.benchmark.BenchmarkMessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqBenchmarkMessageSender implements BenchmarkMessageSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.benchmarks.exchange}")
    private String benchmarkExchange;

    @Value("${rabbitmq.benchmarks.routingKeys.job}")
    private String benchmarkJobRoutingKey;

    @Override
    public void send(BenchmarkJobCreatedEvent event) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(benchmarkExchange, benchmarkJobRoutingKey, event);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(BenchmarkJobAbortCommand command) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(benchmarkExchange, benchmarkJobRoutingKey, command);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }
}
