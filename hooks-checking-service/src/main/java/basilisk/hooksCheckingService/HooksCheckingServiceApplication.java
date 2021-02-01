package basilisk.hooksCheckingService;

import basilisk.hooksCheckingService.domain.HooksRepos.GitBranchRepo;
import basilisk.hooksCheckingService.domain.HooksRepos.GitRepo;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitHookRepositoryImpl;
import basilisk.hooksCheckingService.web.GitBranchCheckingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HooksCheckingServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HooksCheckingServiceApplication.class, args);

        GitBranchRepo repo=new GitBranchRepo("Secure-Bank-And-ATms","Shaheen47",false,"","master");
        GitHookRepository gitHookRepository=new GitHookRepositoryImpl();

        GitBranchCheckingService gitHubChecker=new GitBranchCheckingService(repo,gitHookRepository);
        gitHubChecker.performChecking();
    }

}
