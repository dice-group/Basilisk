package basilisk.jobsManagingService.messaging;

/**
 * @author Fakhr Shaheen
 */
public interface MessageReceiver {

    public void receiveDockerConfig();

    public void receiveGitConfig();
}
