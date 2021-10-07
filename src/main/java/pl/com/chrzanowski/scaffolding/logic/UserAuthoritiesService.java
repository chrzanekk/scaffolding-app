package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.UserAuthorityData;

import java.util.List;

@Service
public class UserAuthoritiesService {

    private UserAuthoritiesJdbcRepository userAuthoritiesJdbcRepository;


    public UserAuthoritiesService(UserAuthoritiesJdbcRepository userAuthoritiesJdbcRepository) {
        this.userAuthoritiesJdbcRepository = userAuthoritiesJdbcRepository;
    }

    public void deleteAuthorities(UserData user) {
        userAuthoritiesJdbcRepository.deleteAuthoritiesFromUser(new UserAuthoritiesFilter(user.getId()));
    }
//tu zmienie na domyślną rejestracyjną rolę USER. bo implementacja z course_platform sie sypie
    public void validateAndCreateAuthorityForUser(UserData user, String[] authorities) {
        if (authorities != null) {
            getAuthoritiesWithRoles(authorities);
            userAuthoritiesJdbcRepository.create(new UserAuthorityData(user, authorities));
        } else {
            throw new IllegalArgumentException("Role can't be empty");
        }
    }

    private void getAuthoritiesWithRoles(String[] authorities) {
        for (int i = 0; i < authorities.length; i++) {
            authorities[i] = UserAuthority.valueOf(authorities[i]).getCodeWithRole();
        }
    }

    public Boolean hasUserAuthority(UserData user, UserAuthority authority) {
        List<UserAuthorityData> authorities =
                userAuthoritiesJdbcRepository.find(new UserAuthoritiesFilter(user.getId(),
                        authority.getCodeWithRole()));
        return !authorities.isEmpty();
    }

    public String[] getAuthoritiesForUser(UserData user) {
        List<UserAuthorityData> authorityData =
                userAuthoritiesJdbcRepository.find(new UserAuthoritiesFilter(user.getId()));
        if (authorityData != null) {
            return getAuthoritiesForUser(authorityData);
        } else {
            throw new IllegalArgumentException("Authorities can't be empty");
        }
    }

    private String[] getAuthoritiesForUser(List<UserAuthorityData> authorityData) {
        String[] authorities = new String[authorityData.size()];
        int i = 0;
        for (UserAuthorityData data : authorityData) {
            authorities[i] = UserAuthority.findByCodeWithRole(data.getAuthority()).getCode();
            i++;
        }
        return authorities;
    }

}
