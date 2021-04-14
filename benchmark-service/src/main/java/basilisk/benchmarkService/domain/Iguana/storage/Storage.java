package basilisk.benchmarkService.domain.Iguana.storage;


import lombok.Getter;

/**
 * @author Fakhr Shaheen
 */


@Getter
public abstract class Storage  {

    private  String className;

    public Storage(String className) {
        this.className = className;
    }
}
