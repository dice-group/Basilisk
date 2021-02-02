package basilisk.hooksCheckingService.repositories;


import basilisk.hooksCheckingService.domain.git.GitHook;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface GitHookRepository extends HookRepository<GitHook>{

    public GitHook findBySha1Hash(String hash);

    public GitHook getLatestHook();

    public Optional<GitHook> findTopByCreatedDateAnd();
}
