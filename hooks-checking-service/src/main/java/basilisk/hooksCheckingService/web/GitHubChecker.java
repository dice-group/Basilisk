package basilisk.hooksCheckingService.web;


import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GitHubService;
import org.eclipse.egit.github.core.service.RepositoryService;


import java.io.IOException;
import java.util.List;


public class GitHubChecker {


    public void check() throws IOException {


        RepositoryService service = new RepositoryService();
        List<Repository> repos= service.getRepositories("Shaheen47");
        for(Repository repo:repos)
        {
            System.out.println("name:"+repo.getName());
        }
        System.out.printf("sdad");


    }
}
