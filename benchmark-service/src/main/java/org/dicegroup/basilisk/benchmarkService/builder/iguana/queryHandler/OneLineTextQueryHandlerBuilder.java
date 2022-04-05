package org.dicegroup.basilisk.benchmarkService.builder.iguana.queryHandler;


import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;

/**
 * @author Fakhr Shaheen
 */
public class OneLineTextQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {


    private final String className="InstancesQueryHandler";

    @Override
    public IguanaTaskQueryHandler build() {
        return new IguanaTaskQueryHandler(className);
    }
}