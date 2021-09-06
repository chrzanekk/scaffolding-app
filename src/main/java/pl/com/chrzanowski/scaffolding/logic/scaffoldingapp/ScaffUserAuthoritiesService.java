package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserAuthorityData;

import java.util.List;

@Service
public class ScaffUserAuthoritiesService {

    private ScaffUserAuthoritiesJdbcRepository scaffUserAuthoritiesJdbcRepository;

    public ScaffUserAuthoritiesService(ScaffUserAuthoritiesJdbcRepository scaffUserAuthoritiesJdbcRepository) {
        this.scaffUserAuthoritiesJdbcRepository = scaffUserAuthoritiesJdbcRepository;
    }

    public void deleteAuthorities(ScaffUserData user) {
        scaffUserAuthoritiesJdbcRepository.deleteAuthoritiesFromUser(new ScaffUserAuthoritiesFilter(user.getId()));
    }
//tu zmienie na domyślną rejestracyjną rolę USER. bo implementacja z course_platform sie sypie
    public void validateAndCreateAuthorityForUser(ScaffUserData user, String[] authorities) {
        if (authorities != null) {
            getAuthoritiesWithRoles(authorities);
            scaffUserAuthoritiesJdbcRepository.create(new ScaffUserAuthorityData(user, authorities));
        } else {
            throw new IllegalArgumentException("Role can't be empty");
        }
    }

    private void getAuthoritiesWithRoles(String[] authorities) {
        for (int i = 0; i < authorities.length; i++) {
            authorities[i] = ScaffUserAuthority.valueOf(authorities[i]).getCodeWithRole();
        }
    }

    public Boolean hasUserAuthority(ScaffUserData user, ScaffUserAuthority authority) {
        List<ScaffUserAuthorityData> authorities =
                scaffUserAuthoritiesJdbcRepository.find(new ScaffUserAuthoritiesFilter(user.getId(),
                        authority.getCodeWithRole()));
        return !authorities.isEmpty();
    }

    public String[] getAuthoritiesForUser(ScaffUserData user) {
        List<ScaffUserAuthorityData> authorityData =
                scaffUserAuthoritiesJdbcRepository.find(new ScaffUserAuthoritiesFilter(user.getId()));
        if (authorityData != null) {
            return getAuthoritiesForUser(authorityData);
        } else {
            throw new IllegalArgumentException("Authorities can't be empty");
        }
    }

    private String[] getAuthoritiesForUser(List<ScaffUserAuthorityData> authorityData) {
        String[] authorities = new String[authorityData.size()];
        int i = 0;
        for (ScaffUserAuthorityData data : authorityData) {
            authorities[i] = ScaffUserAuthority.findByCodeWithRole(data.getAuthority()).getCode();
            i++;
        }
        return authorities;
    }
}
