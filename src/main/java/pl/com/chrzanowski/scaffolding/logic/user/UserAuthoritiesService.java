package pl.com.chrzanowski.scaffolding.logic.user;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.users.UserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.users.UserAuthorityData;
import pl.com.chrzanowski.scaffolding.domain.users.UserData;
import pl.com.chrzanowski.scaffolding.domain.users.UsersFilter;
import pl.com.chrzanowski.scaffolding.logic.utils.MapToListConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getString;

@Service
public class UserAuthoritiesService {

    private UserAuthoritiesJdbcRepository userAuthoritiesJdbcRepository;
    private UserJdbcRepository userJdbcRepository;


    public UserAuthoritiesService(UserAuthoritiesJdbcRepository userAuthoritiesJdbcRepository,
                                  UserJdbcRepository userJdbcRepository) {
        this.userAuthoritiesJdbcRepository = userAuthoritiesJdbcRepository;
        this.userJdbcRepository = userJdbcRepository;
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
                    MapToListConverter.getUserList(userJdbcRepository.find(new UsersFilter(getLong(row, "user_id")))).get(0),
                    getString(row, "authority")
            ));
        }
        return list;
    }

}
