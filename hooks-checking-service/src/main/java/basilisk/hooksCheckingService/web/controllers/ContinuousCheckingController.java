package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.services.continuesCheckingServices.ContinuesCheckingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fakhr Shaheen
 */

@RestController
@RequestMapping("continuousChecking/")
public class ContinuousCheckingController {

    private ContinuesCheckingService continuesCheckingService;

    public ContinuousCheckingController(ContinuesCheckingService continuesCheckingService) {
        this.continuesCheckingService = continuesCheckingService;
    }

    @PostMapping(path = "/start")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> start() throws InterruptedException {
        if(continuesCheckingService.isRunning())
            return new ResponseEntity<>("the service is already running.",HttpStatus.OK);
        else
        {
                continuesCheckingService.start();
            return new ResponseEntity<>("the service has started.",HttpStatus.OK);
        }
    }

    @PostMapping(path = "/stop")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String>  stop() {
        if (!continuesCheckingService.isRunning())
            return new ResponseEntity<>("The service is not running.",HttpStatus.OK);
        else {
            continuesCheckingService.stop();
            return new ResponseEntity<>("The service is stopped.",HttpStatus.OK);
        }

    }
        @GetMapping("/status")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<String> getStatus() {
        if(continuesCheckingService.isRunning())
            return new ResponseEntity<>("The service is running.",HttpStatus.OK);
        else
            return new ResponseEntity<>("The service is not running.",HttpStatus.OK);
    }


}
