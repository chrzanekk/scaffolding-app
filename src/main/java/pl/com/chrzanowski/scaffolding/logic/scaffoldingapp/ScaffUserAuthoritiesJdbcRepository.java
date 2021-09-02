package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserAuthoritiesFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserAuthorityData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getString;

@Service
public class ScaffUserAuthoritiesJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private ScaffUserJdbcRepository scaffUserJdbcRepository;

    public ScaffUserAuthoritiesJdbcRepository(JdbcTemplate jdbcTemplate, ScaffUserJdbcRepository scaffUserJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.scaffUserJdbcRepository = scaffUserJdbcRepository;
    }

    void create(ScaffUserAuthorityData data) {
        String query = "INSERT INTO user_authorities (user_id, authority) values (?, ?)";
        for (int i = 0; i < data.getAuthorities().length; i++) {
            jdbcTemplate.update(query, data.getUser().getId(), data.getAuthorities()[i]);
        }
    }

    void deleteAuthoritiesFromUser(ScaffUserAuthoritiesFilter filter) {
        String query = "DELETE FROM user_authorities WHERE user_id =" + filter.getUserId();
        jdbcTemplate.update(query);
    }

    List<ScaffUserAuthorityData> find(ScaffUserAuthoritiesFilter filter) {

        List<ScaffUserAuthorityData> authorities = new ArrayList<>();

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
            authorities.add(new ScaffUserAuthorityData(
                    getLong(row, "id"),
                    scaffUserJdbcRepository.find(new ScaffUsersFilter(getLong(row, "user_id"))).get(0),
                    getString(row, "authority")
            ));
        }
        return authorities;
    }
}
