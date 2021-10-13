package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @CacheEvict(value = CacheType.MENU, allEntries = true)
    public void invalidateMenu() {

    }

    @CacheEvict(value = CacheType.USER_ROLE, allEntries = true)
    public void invalidateUserRole() {

    }

    @CacheEvict(value = CacheType.DICTIONARIES, allEntries = true)
    public void invalidateDictionaries() {

    }

    @CacheEvict(value = CacheType.DICTIONARY_USER, allEntries = true)
    public void invalidateUsers() {

    }

    @CacheEvict(value = CacheType.DICTIONARIES_BY_ID, allEntries = true)
    public void invalidateDictionariesById() {
    }

}
