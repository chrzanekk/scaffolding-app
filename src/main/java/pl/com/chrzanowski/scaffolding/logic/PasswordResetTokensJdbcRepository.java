package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.PasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.PasswordResetTokensFilter;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class PasswordResetTokensJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private ScaffUsersService scaffUsersService;

    public PasswordResetTokensJdbcRepository(JdbcTemplate jdbcTemplate, ScaffUsersService scaffUsersService) {
        this.jdbcTemplate = jdbcTemplate;
        this.scaffUsersService = scaffUsersService;
    }

    void create(PasswordResetTokenData data) {
        String query = "INSERT INTO password_reset_tokens (user_id, value, expiration_datetime, used) VALUES (?, ?, " +
                "?, ?)";
        jdbcTemplate.update(query, data.getUser().getId(), data.getValue(), data.getExpirationDatetime(),
                data.getUsed());
    }

    List<PasswordResetTokenData> find(PasswordResetTokensFilter filter) {

        String query = "SELECT * FROM password_reset_tokens";

        if (filter != null) {

            query += " WHERE 1=1";

            if (filter.getValue() != null) {
                query += " AND value = '" + filter.getValue() + "'";
            }

        }


        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        List<PasswordResetTokenData> list = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            list.add(new PasswordResetTokenData(
                    getLong(row, "id"),
                    scaffUsersService.find(new UsersFilter(getLong(row, "user_id"))).get(0),
                    getString(row, "value"),
                    getDateTime(row, "expiration_datetime"),
                    getBoolean(row, "used")
            ));
        }
        return list;
    }

    void update(PasswordResetTokenData data) {
        String query = "UPDATE password_reset_tokens SET user_id = ?, value = ?, expiration_datetime = ?, used = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, data.getUser().getId(), data.getValue(), data.getExpirationDatetime(), data.getUsed()
                , data.getId());
    }
}
