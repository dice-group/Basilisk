package basilisk.benchmarkService.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author Fakhr Shaheen
 */
@SuperBuilder
@Getter
public class TripleStore extends BaseEntity{

    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;

}
