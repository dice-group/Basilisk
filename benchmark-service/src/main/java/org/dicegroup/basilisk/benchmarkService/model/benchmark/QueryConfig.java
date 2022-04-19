package org.dicegroup.basilisk.benchmarkService.model.benchmark;


import lombok.Getter;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;

/**
 * @author Fakhr Shaheen
 */

@Getter
public class QueryConfig extends BaseEntity {

    String name;
    String url;
    boolean isActive;

    public QueryConfig(String name,String url)
    {
        this.name=name;
        this.url=url;
    }

}
