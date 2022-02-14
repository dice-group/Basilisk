package basilisk.jobsManagingService.events.BenchmarkJob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Fakhr Shaheen
 */
@Getter
@Setter
@AllArgsConstructor
public class BenchmarkJobFailedEvent implements Serializable {

    Long jobId;

}
