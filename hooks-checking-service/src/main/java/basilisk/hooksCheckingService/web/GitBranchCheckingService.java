package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.Hooks.Hook;

import java.io.IOException;

public class GitBranchCheckingService implements GitCheckingService{
    @Override
    public void checkForNewVersion() throws IOException {

    }

    @Override
    public Hook getHook() {
        return null;
    }
}
