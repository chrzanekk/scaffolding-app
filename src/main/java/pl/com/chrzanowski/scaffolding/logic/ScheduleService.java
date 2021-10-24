package pl.com.chrzanowski.scaffolding.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;


@Service
public class ScheduleService {
    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);
    private CacheService cacheService;
    private ApplicationConfig applicationConfig;

    public ScheduleService(CacheService cacheService,  ApplicationConfig applicationConfig) {
        this.cacheService = cacheService;
        this.applicationConfig = applicationConfig;
    }

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void scheduleFixedDelayTask() {
        cacheService.invalidateDictionaries();
        cacheService.invalidateMenu();
        cacheService.invalidateUserRole();
        cacheService.invalidateUsers();
        cacheService.invalidateDictionariesById();
        log.debug("Caches invalidated");
    }


}
