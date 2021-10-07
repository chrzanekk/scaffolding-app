package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.UserAuthorityData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;


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

    List<Map<String, Object>> find(UserAuthoritiesFilter filter) {

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


        return jdbcTemplate.queryForList(query);
    }
}
