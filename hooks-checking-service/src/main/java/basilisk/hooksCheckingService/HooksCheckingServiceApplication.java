package basilisk.hooksCheckingService;

import basilisk.hooksCheckingService.web.GitHubChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HooksCheckingServiceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HooksCheckingServiceApplication.class, args);
        GitHubChecker gitHubChecker=new GitHubChecker();
        gitHubChecker.check();
    }

}
