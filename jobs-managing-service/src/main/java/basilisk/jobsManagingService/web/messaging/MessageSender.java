package basilisk.jobsManagingService.web.messaging;


import basilisk.jobsManagingService.core.exception.MessageSendingExecption;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;

/**
 * @author Fakhr Shaheen
 */
public interface MessageSender {


    public void send(BenchmarkJobCreatedEvent event) throws MessageSendingExecption;
    public void send(BenchmarkJobAbortCommand command) throws MessageSendingExecption;
}
