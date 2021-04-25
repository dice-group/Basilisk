package basilisk.jobsManagingService.web.messaging;

import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class RabbitMqMessageSender implements MessageSender{
    @Override
    public void send(BenchmarkJobCreatedEvent event) {

    }

    @Override
    public void send(BenchmarkJobAbortCommand command) {

    }
}
