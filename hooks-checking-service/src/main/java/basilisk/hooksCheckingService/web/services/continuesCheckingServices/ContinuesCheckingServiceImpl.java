package basilisk.hooksCheckingService.web.services.continuesCheckingServices;

import basilisk.hooksCheckingService.core.TimingStrategy;
import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Fakhr Shaheen
 */

@Component
public class ContinuesCheckingServiceImpl implements ContinuesCheckingService{

    public ContinuesCheckingServiceImpl(CheckingService checkingService, TimingStrategy timingStrategy) {
        this.checkingService = checkingService;
        this.timingStrategy = timingStrategy;
    }

    CheckingService checkingService;
    @Setter
    @Getter
    TimingStrategy timingStrategy;

    @Override
    public void check() throws InterruptedException {
        while (true)
        {
            timingStrategy.sleep();
            try {
                checkingService.performChecking();
            }
            catch (GithubException e)
            {
                //ToDo log
                System.out.println("not valid git thing");
            }
        }
    }
}
