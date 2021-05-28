package basilisk.jobsManagingService.events.BenchmarkJob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fakhr Shaheen
 */
@Getter
@Setter
@AllArgsConstructor
public class BenchmarkJobFinishedEvent {
    Long jobId;
}
