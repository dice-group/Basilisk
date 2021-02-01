package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.Hooks.Hook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HookRepository extends JpaRepository<Hook, UUID> {
}
