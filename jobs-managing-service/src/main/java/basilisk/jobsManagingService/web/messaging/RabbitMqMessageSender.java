package basilisk.jobsManagingService.web.messaging;

import basilisk.jobsManagingService.core.exception.MessageSendingExecption;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class RabbitMqMessageSender implements MessageSender{


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${jobs.benchmark.rabbitmq.exchange}")
    private String exchange;

    @Value("${jobs.benchmark.rabbitmq.createdEventRoutingkey}")
    private String createdEventRoutingkeys;

    @Value("${jobs.benchmark.rabbitmq.abortCommandRoutingkey}")
    private String abortCommandRoutingkeys;




    @Override
    public void send(BenchmarkJobCreatedEvent event) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, createdEventRoutingkeys, event);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(BenchmarkJobAbortCommand command) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, abortCommandRoutingkeys, command);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }
}
