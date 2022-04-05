package org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.git;

import org.dicegroup.basilisk.events.hooks.hook.GitCommitEvent;
import org.dicegroup.basilisk.hooksCheckingService.core.exception.GithubException;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitHook;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import org.dicegroup.basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.CheckingService;
import org.dicegroup.basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;


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
        for (GitRepo gitrepo : getRelatedGitRepos()) {
            try {
                checkRepo(gitrepo);
            } catch (GithubException e) {
                logger.error("Problem while checking the Git repo");
            }
        }
    }

    protected abstract Iterable<GitRepo> getRelatedGitRepos();

    protected abstract void checkRepo(GitRepo gitrepo) throws GithubException;

    protected void checkHooks(GitRepo gitRepo, GHRepository repo, String sha1) throws IOException {
        Optional<GitHook> foundHook = this.gitHookRepository.findByCommitSha1(sha1);

        if (foundHook.isEmpty()) {
            GHCommit commit = repo.getCommit(sha1);
            GitHook hook = createGitHook(gitRepo, commit);

            this.gitHookRepository.save(hook);
            this.messageSender.send(createGitCommitAddedEvent(gitRepo, hook));
        }
    }

    protected GHRepository getRepoFromGitHub(GitRepo gitRepo) throws IOException {
        GitHub github;
        if (gitRepo.isPrivate()) {
            github = new GitHubBuilder().withOAuthToken(gitRepo.getOAuthToken()).build();
        } else {
            github = GitHub.connectAnonymously();
        }

        logger.info("GitHub rate limit: {}", github.getRateLimit());

        return github.getUser(gitRepo.getRepoOwner()).getRepository(gitRepo.getRepoName());
    }

    private GitHook createGitHook(GitRepo gitRepo, GHCommit commit) throws IOException {
        return GitHook.builder()
                .gitRepo(gitRepo)
                .commitCreationDate(commit.getCommitDate())
                .commitSha1(commit.getSHA1())
                .commitUrl(commit.getHtmlUrl().toString())
                .build();
    }

    private GitCommitEvent createGitCommitAddedEvent(GitRepo gitRepo, GitHook gitHook) {
        return GitCommitEvent.builder()
                .commitSha1(gitHook.getCommitSha1())
                .repoId(gitRepo.getId())
                .url(gitHook.getCommitUrl())
                .build();
    }
}
