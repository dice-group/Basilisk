package basilisk.hooksCheckingService.domain.docker;


import basilisk.hooksCheckingService.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "docker_image")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = DockerImage.class)
public class DockerImage extends BaseEntity {


    @Column(name = "digest")
    String digest;

    @Column(name = "last_pushed")
    Date lastPushedDate;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private DockerRepo dockerRepo;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dockerImage")
    private Set<DockerTag> tags;


    @Override
    public String toString() {
        return "DockerImage [digest=" + digest + ", lastPushedDate=" + lastPushedDate + "]";
    }

}
