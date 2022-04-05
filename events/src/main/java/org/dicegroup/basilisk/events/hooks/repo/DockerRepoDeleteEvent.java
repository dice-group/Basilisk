package org.dicegroup.basilisk.events.hooks.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DockerRepoDeleteEvent implements Serializable {

    private Long id;

}
