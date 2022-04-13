package org.dicegroup.basilisk.benchmarkService.services;

import org.aksw.iguana.cc.controller.MainController;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IguanaService {

    public void startBenchmark() throws IOException {
        MainController controller = new MainController();


        // temporäre files?
        controller.start("path/to/config.json", true);
    }

}
