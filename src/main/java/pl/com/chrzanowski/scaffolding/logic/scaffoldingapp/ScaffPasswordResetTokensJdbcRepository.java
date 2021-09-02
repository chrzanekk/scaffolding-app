package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffPasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffPasswordResetTokensFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class ScaffPasswordResetTokensJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private ScaffUsersService scaffUsersService;

    public ScaffPasswordResetTokensJdbcRepository(JdbcTemplate jdbcTemplate, ScaffUsersService scaffUsersService) {
        this.jdbcTemplate = jdbcTemplate;
        this.scaffUsersService = scaffUsersService;
    }

    void create(ScaffPasswordResetTokenData data) {
        String query = "INSERT INTO password_reset_tokens (user_id, value, expiration_datetime, used) VALUES (?, ?, " +
                "?, ?)";
        jdbcTemplate.update(query, data.getUser().getId(), data.getValue(), data.getExpirationDatetime(),
                data.getUsed());
    }

    List<ScaffPasswordResetTokenData> find(ScaffPasswordResetTokensFilter filter) {

        String query = "SELECT * FROM password_reset_tokens";

        if (filter != null) {

            query += " WHERE 1=1";

            if (filter.getValue() != null) {
                query += " AND value = '" + filter.getValue() + "'";
            }

        }


        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        List<ScaffPasswordResetTokenData> list = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            list.add(new ScaffPasswordResetTokenData(
                    getLong(row, "id"),
                    scaffUsersService.find(new ScaffUsersFilter(getLong(row, "user_id"))).get(0),
                    getString(row, "value"),
                    getDateTime(row, "expiration_datetime"),
                    getBoolean(row, "used")
            ));
        }
        return list;
    }

    void update(ScaffPasswordResetTokenData data) {
        String query = "UPDATE password_reset_tokens SET user_id = ?, value = ?, expiration_datetime = ?, used = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, data.getUser().getId(), data.getValue(), data.getExpirationDatetime(), data.getUsed()
                , data.getId());
    }
}
