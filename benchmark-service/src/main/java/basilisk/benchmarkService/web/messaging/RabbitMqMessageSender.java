package basilisk.benchmarkService.web.messaging;

import basilisk.benchmarkService.events.BenchmarkJobAbortedEvent;
import basilisk.benchmarkService.events.BenchmarkJobFailedEvent;
import basilisk.benchmarkService.events.BenchmarkJobFinishedEvent;
import basilisk.benchmarkService.events.BenchmarkJobStartedEvent;
import basilisk.benchmarkService.exception.MessageSendingExecption;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class RabbitMqMessageSender implements MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${jobs.benchmark.rabbitmq.exchange}")
    private String exchange;

    @Value("${jobs.benchmark.rabbitmq.startedEventRoutingkey}")
    private String startedEventRoutingkeys;

    @Value("${jobs.benchmark.rabbitmq.finishedEventRoutingkey}")
    private String finishedEventRoutingkeys;

    @Value("${jobs.benchmark.rabbitmq.failedEventRoutingkey}")
    private String failedEventRoutingkeys;

    @Value("${jobs.benchmark.rabbitmq.abortedEventRoutingkey}")
    private String abortedEventRoutingkeys;




    @Override
    public void send(BenchmarkJobStartedEvent benchmarkJobStartedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, startedEventRoutingkeys, benchmarkJobStartedEvent);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(BenchmarkJobAbortedEvent benchmarkJobAbortedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, abortedEventRoutingkeys, benchmarkJobAbortedEvent);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(BenchmarkJobFinishedEvent benchmarkJobFinishedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, finishedEventRoutingkeys, benchmarkJobFinishedEvent);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(BenchmarkJobFailedEvent benchmarkJobFailedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, failedEventRoutingkeys, benchmarkJobFailedEvent);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }
}
