package basilisk.benchmarkService.domain.Iguana;

import basilisk.benchmarkService.domain.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Builder
@NoArgsConstructor
@Entity
public class IguanaConnection extends BaseEntity {

    private String name;
    private String endpoint;
    private String updateEndpoint;


    public IguanaConnection(String name, String endpoint, String updateEndpoint) {
        this.name = name;
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;
    }

}
