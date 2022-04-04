package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TripleStore extends BaseEntity {
    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;
}
