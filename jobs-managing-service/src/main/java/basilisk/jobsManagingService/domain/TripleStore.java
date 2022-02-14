package basilisk.jobsManagingService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Fakhr Shaheen
 */
@SuperBuilder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TripleStore extends BaseEntity{

    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;
    
    @ManyToOne
    private Repo dockerRepo;
    @ManyToOne
    private Repo gitRepo;
}
