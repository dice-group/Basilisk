package basilisk.hooksCheckingService.domain.docker;


import basilisk.hooksCheckingService.domain.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Fakhr Shaheen
 */

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "docker_image")
public class DockerImage extends BaseEntity {


    @JsonProperty("digest")
    @Column(name = "digest")
    String digest;

    @JsonProperty("last_pushed")
    @Column(name = "last_pushed")
    Date lastPushedDate;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private DockerRepo dockerRepo;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dockerImage")
    private Set<DockerTag> tags;

    public String getDigest() {
        return digest;
    }

    public Date getLastPushedDate() {
        return lastPushedDate;
    }

    @JsonIgnore
    public DockerRepo getDockerRepo() {
        return dockerRepo;
    }

    @JsonIgnore
    public Set<DockerTag> getTags() {
        return tags;
    }
}
