package org.dicegroup.basilisk.benchmarkService.services;

import org.aksw.iguana.cc.config.IguanaConfig;
import org.aksw.iguana.cc.controller.MainController;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IguanaService {

    public String startBenchmark() throws IOException {
        MainController controller = new MainController();

        // temporäre files?
        controller.start("/home/fabian/dev/bachelor/Basilisk/example_benchmark/iguana-config.yml", true);

        return "started?";
    }

}
