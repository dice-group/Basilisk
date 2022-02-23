package basilisk.hooksCheckingService.services.checkingServices.git;

import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.services.checkingServices.CheckingService;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public abstract class GitCheckingService implements CheckingService {

    private static final Logger logger = LoggerFactory.getLogger(GitCheckingService.class);

    protected final GitHookRepository gitHookRepository;
    protected final GitRepoRepository gitRepoRepository;
    protected final MessageSender messageSender;

    public GitCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender messageSender) {
        this.gitHookRepository = gitHookRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.messageSender = messageSender;
    }

    public void performChecking() {

        //get the github repos
        Iterable<GitRepo> gitRepos = getRelatedGitRepos();
        //go through them
        for (GitRepo gitrepo : gitRepos) {
            //do the logic for checking
            try {
                checkRepo(gitrepo);
            } catch (GithubException e) {
                //ToDo log
                System.out.println("not valid git thing");
            }
            //
        }

    }

    protected abstract Iterable<GitRepo> getRelatedGitRepos();

    protected abstract void checkRepo(GitRepo gitrepo) throws GithubException;


    protected GHRepository getRepoFromGitHub(GitRepo gitRepo) throws IOException {
        GitHub github;
        if (gitRepo.isPrivate())
            github = new GitHubBuilder().withOAuthToken(gitRepo.getOAuthToken()).build();
        else
            github =GitHub.connectAnonymously();

        logger.info("GitHub rate limit: {}", github.getRateLimit());

        return github.getUser(gitRepo.getRepoOwner()).getRepository(gitRepo.getRepoName());
    }




}
