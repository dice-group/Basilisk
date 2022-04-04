package org.dicegroup.basilisk.benchmarkService.domain.benchamrking;


import org.dicegroup.basilisk.benchmarkService.domain.BaseEntity;
import lombok.Getter;

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
