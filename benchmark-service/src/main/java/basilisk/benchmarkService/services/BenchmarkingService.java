package basilisk.benchmarkService.services;


/**
 * @author Fakhr Shaheen
 */
public interface BenchmarkingService {

    /**
     *
     * @param iguanaFile iguana connfiguration as json or yaml file
     */
    public void performBenchmark(String iguanaFile);
}
