package basilisk.hooksCheckingService.messaging;

import basilisk.hooksCheckingService.domain.Hooks.Hook;

public interface HookMessageSender {

    public void sendHookMessage(Hook hook);
}
