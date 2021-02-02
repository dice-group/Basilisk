package basilisk.hooksCheckingService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fakhr Shaheen
 */

@Repository
public interface HubRepoRepository<HubRepo> extends CrudRepository<HubRepo,Long> {
}
