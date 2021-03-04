package basilisk.hooksCheckingService.web.services.checkingServices.git;

import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.Iterator;

/***
 * @author Fakhr Shaheen
 * This class follows the templte method pattern to difine a common steps that will be followed by all child classes
 */
public abstract class GitCheckingService implements CheckingService {


    protected final GitHookRepository gitHookRepository;
    protected final GitRepoRepository gitRepoRepository;
    protected final HookMessageSender hookMessageSender;

    public GitCheckingService( GitRepoRepository gitRepoRepository,GitHookRepository gitHookRepository,HookMessageSender hookMessageSender) {
        this.gitHookRepository = gitHookRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.hookMessageSender = hookMessageSender;
    }

    public void performChecking() {

        //get the github repos
        Iterable<GitRepo> gitRepos = getRelatedGitRepos();
        //go through them
        for (GitRepo gitrepo : gitRepos) {
            //do the logic for checking
            try {
                checkForNewVersion(gitrepo);
            } catch (GithubException e) {
                //ToDo log
                System.out.println("not valid git thing");
            }
            //
        }

    }

    protected abstract Iterable<GitRepo> getRelatedGitRepos();

    protected abstract void checkForNewVersion(GitRepo gitrepo) throws GithubException;


    protected GHRepository getRepoFromGitHub(GitRepo gitRepo) throws IOException {
        GitHub github;
        if (gitRepo.isPrivate())
            github = new GitHubBuilder().withOAuthToken(gitRepo.getOAuthToken()).build();
        else
            github =GitHub.connectAnonymously();

        GHRepository repo = github.getUser(gitRepo.getRepoOwner()).getRepository(gitRepo.getRepoName());

        return repo;
    }




}
