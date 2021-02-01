package basilisk.hooksCheckingService.repositories;



import java.util.List;


public interface HookRepository<Hook>{

    List<Hook> getAllHooks();


    Hook getLatestHook();

    void addNewHook(Hook hook);

    Hook findBySha1Hash(String hash);

}
