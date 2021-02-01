package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.Hooks.GitHook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GitHookRepositoryImpl implements GitHookRepository{

    //just for testing
    private List<GitHook> hooks;

    public GitHookRepositoryImpl() {

        hooks=new ArrayList<>();
    }

    @Override
    public List<GitHook> getAllHooks() {
        return null;
    }

    @Override
    public GitHook getLatestHook() {
        return null;
    }

    @Override
    public void addNewHook(GitHook gitHook) {

    }

    @Override
    public GitHook findBySha1Hash(String hash) {
        GitHook foundHook = hooks.stream()
                .filter(hook -> hash.equals(hook.getSha1Hash()))
                .findAny()
                .orElse(null);
        return foundHook;
    }
}
