package org.dicegroup.basilisk.benchmarkService.model.iguana.task;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private String className;

    private TaskConfiguration configuration;

}
