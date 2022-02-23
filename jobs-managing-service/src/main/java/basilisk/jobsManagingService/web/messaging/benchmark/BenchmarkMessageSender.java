package basilisk.jobsManagingService.web.messaging.benchmark;


import basilisk.jobsManagingService.core.exception.MessageSendingExecption;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;
import basilisk.jobsManagingService.model.repo.GitRepo;


public interface BenchmarkMessageSender {

    public void send(BenchmarkJobCreatedEvent event) throws MessageSendingExecption;
    public void send(BenchmarkJobAbortCommand command) throws MessageSendingExecption;

}
