package basilisk.hooksCheckingService;

import basilisk.hooksCheckingService.domain.HubRepos.GitBranchHubRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.messaging.HookMessageSenderDumpImpl;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitHookRepositoryImpl;
import basilisk.hooksCheckingService.web.GitBranchCheckingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HooksCheckingServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HooksCheckingServiceApplication.class, args);

        GitBranchHubRepo repo=new GitBranchHubRepo("Secure-Bank-And-ATms","Shaheen47",false,"","master");
        GitHookRepository gitHookRepository=new GitHookRepositoryImpl();
        HookMessageSender hookMessageSender=new HookMessageSenderDumpImpl();

        GitBranchCheckingService gitHubChecker=new GitBranchCheckingService(repo,gitHookRepository,hookMessageSender);
        gitHubChecker.performChecking();
    }

}
