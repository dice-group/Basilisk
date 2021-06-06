package basilisk.benchmarkService.web.messaging;

import basilisk.benchmarkService.events.BenchmarkJobAbortedEvent;
import basilisk.benchmarkService.events.BenchmarkJobFailedEvent;
import basilisk.benchmarkService.events.BenchmarkJobFinishedEvent;
import basilisk.benchmarkService.events.BenchmarkJobStartedEvent;
import basilisk.benchmarkService.exception.MessageSendingExecption;

/**
 * @author Fakhr Shaheen
 */
public interface MessageSender {

    public void send(BenchmarkJobStartedEvent benchmarkJobStartedEvent) throws MessageSendingExecption;
    public void send(BenchmarkJobAbortedEvent benchmarkJobAbortedEvent) throws MessageSendingExecption;
    public void send(BenchmarkJobFinishedEvent benchmarkJobFinishedEvent) throws MessageSendingExecption;
    public void send(BenchmarkJobFailedEvent benchmarkJobFailedEvent) throws MessageSendingExecption;

}
