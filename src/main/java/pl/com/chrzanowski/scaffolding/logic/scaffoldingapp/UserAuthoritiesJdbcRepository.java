package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerAuthorityData;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomersFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserAuthorityData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UsersFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getString;

@Service
public class UserAuthoritiesJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private UserJdbcRepository userJdbcRepository;

    public UserAuthoritiesJdbcRepository(JdbcTemplate jdbcTemplate, UserJdbcRepository userJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userJdbcRepository = userJdbcRepository;
    }

    void create(UserAuthorityData data) {
        String query = "INSERT INTO user_authorities (user_id, authority) values (?, ?)";
        for (int i = 0; i < data.getAuthorities().length; i++) {
            jdbcTemplate.update(query, data.getUser().getId(), data.getAuthorities()[i]);
        }
    }

    void deleteAuthoritiesFromUser(UserAuthoritiesFilter filter) {
        String query = "DELETE FROM user_authorities WHERE user_id =" + filter.getUserId();
        jdbcTemplate.update(query);
    }

    List<UserAuthorityData> find(UserAuthoritiesFilter filter) {

        List<UserAuthorityData> authorities = new ArrayList<>();

        String query = "SELECT * FROM user_authorities";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getAuthority() != null) {
                query += " AND authority = '" + filter.getAuthority() + "'";
            }

            if (filter.getUserId() != null) {
                query += " AND user_id = " + filter.getUserId();
            }
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> row : rows) {
            authorities.add(new UserAuthorityData(
                    getLong(row, "id"),
                    userJdbcRepository.find(new UsersFilter(getLong(row, "user_id"))).get(0),
                    getString(row, "authority")
            ));
        }
        return authorities;
    }
}
