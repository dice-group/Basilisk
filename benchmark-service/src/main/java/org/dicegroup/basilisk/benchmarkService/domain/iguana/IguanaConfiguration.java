package org.dicegroup.basilisk.benchmarkService.domain.iguana;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.storage.Storage;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.Task;

import java.util.List;


@Getter
@Setter
public class IguanaConfiguration {

    @JsonProperty("datasets")
    private List<DataSet> dataSets;

    private List<Connection> connections;

    private List<Task> tasks;

    private List<Storage> storages;

    private List<Metric> metrics;

}
