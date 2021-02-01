package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.Hooks.Hook;
import basilisk.hooksCheckingService.domain.HooksRepos.GitRepo;

import java.io.IOException;

public class GitReleaseCheckingService implements GitCheckingService{

    GitRepo gitRepo;

    @Override
    public void checkForNewVersion() throws IOException {

    }

    @Override
    public Hook getHook() {
        return null;
    }
}
