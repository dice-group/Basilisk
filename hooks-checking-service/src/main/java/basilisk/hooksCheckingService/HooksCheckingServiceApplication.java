package basilisk.hooksCheckingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HooksCheckingServiceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HooksCheckingServiceApplication.class, args);
        GitHubCheckingServiceImpl gitHubChecker=new GitHubCheckingServiceImpl();
        gitHubChecker.check();
    }

}
