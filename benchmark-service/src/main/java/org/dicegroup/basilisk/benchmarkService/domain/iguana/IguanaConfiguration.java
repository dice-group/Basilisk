package org.dicegroup.basilisk.benchmarkService.domain.iguana;


import lombok.Getter;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.storage.Storage;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.Task;

import java.util.List;


@Getter
@Setter
public class IguanaConfiguration {

    private List<DataSet> dataSets;

    private List<Connection> connections;

    private List<Task> tasks;

    private List<Storage> storages;

    private List<Metric> metrics;

}
