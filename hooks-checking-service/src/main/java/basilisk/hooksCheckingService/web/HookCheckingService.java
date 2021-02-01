package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.Hooks.Hook;

import java.io.IOException;

public interface HookCheckingService {

    public void checkForNewVersion() throws IOException;

    public Hook getHook();


}
