package basilisk.benchmarkService.domain.Iguana;

import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author Fakhr Shaheen
 */
@Builder
@NoArgsConstructor
public class IguanaConnection  {

    private String name;
    private String endpoint;
    private String updateEndpoint;


    public IguanaConnection(String name, String endpoint, String updateEndpoint) {
        this.name = name;
        this.endpoint = endpoint;
        this.updateEndpoint=updateEndpoint;
    }

}
