package basilisk.hooksCheckingService.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fakhr Shaheen
 */
@Repository
public interface HookRepository<Hook> extends CrudRepository<Hook,Long> {
}
