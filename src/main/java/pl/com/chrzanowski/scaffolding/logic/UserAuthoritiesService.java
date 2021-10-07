package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.UserAuthorityData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;

@Service
public class UserAuthoritiesService {

    private UserAuthoritiesJdbcRepository userAuthoritiesJdbcRepository;
    private UserService userService;


    public UserAuthoritiesService(UserAuthoritiesJdbcRepository userAuthoritiesJdbcRepository, UserService userService) {
        this.userAuthoritiesJdbcRepository = userAuthoritiesJdbcRepository;
        this.userService = userService;
    }

    public void deleteAuthorities(UserData user) {
        userAuthoritiesJdbcRepository.deleteAuthoritiesFromUser(new UserAuthoritiesFilter(user.getId()));
    }

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
                getUserAuthorities(userAuthoritiesJdbcRepository.find(new UserAuthoritiesFilter(user.getId(),
                        authority.getCodeWithRole())));
        return !authorities.isEmpty();
    }

    public String[] getAuthoritiesForUser(UserData user) {
        List<UserAuthorityData> authorityData =
                getUserAuthorities(userAuthoritiesJdbcRepository.find(new UserAuthoritiesFilter(user.getId())));
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

    private List<UserAuthorityData> getUserAuthorities(List<Map<String, Object>> data) {
        List<UserAuthorityData> list = new ArrayList<>();

        for (Map<String, Object> row : data) {
            list.add(new UserAuthorityData(
                    getLong(row, "id"),
                    userService.find(new UsersFilter(getLong(row, "user_id"))).get(0),
                    getString(row, "authority")
            ));
        }
        return list;
    }

}
