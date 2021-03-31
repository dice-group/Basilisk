package basilisk.jobsManagingService.web.messaging;


import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;

/**
 * @author Fakhr Shaheen
 */
public interface MessageSender {

    public void send(BenchmarkJobCreatedEvent event);
    public void send(BenchmarkJobAbortCommand command);
}
