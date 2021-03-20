package basilisk.hooksCheckingService.repositories;


import basilisk.hooksCheckingService.domain.git.GitHook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface GitHookRepository extends CrudRepository<GitHook,Long> {


    public Optional<GitHook> findByCommitSha1(String sha1);


}
