package basilisk.benchmarkService.web.messaging;

import basilisk.benchmarkService.events.BenchmarkJobAbortCommand;
import basilisk.benchmarkService.events.BenchmarkJobCreatedEvent;

/**
 * @author Fakhr Shaheen
 */
public interface MessageReceiver {

    void receive(BenchmarkJobCreatedEvent event);
    void receive(BenchmarkJobAbortCommand command);
}
