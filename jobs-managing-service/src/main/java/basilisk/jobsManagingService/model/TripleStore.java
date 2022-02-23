package basilisk.jobsManagingService.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Fakhr Shaheen
 */
@Entity
@Table
@SuperBuilder
@NoArgsConstructor
public class TripleStore extends BaseEntity{
    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;
}
