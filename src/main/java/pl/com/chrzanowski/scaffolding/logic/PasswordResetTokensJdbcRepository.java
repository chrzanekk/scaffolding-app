package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.PasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.PasswordResetTokensFilter;

import java.util.List;
import java.util.Map;

@Service
public class PasswordResetTokensJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private UserService userService;

    public PasswordResetTokensJdbcRepository(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    void create(PasswordResetTokenData data) {
        String query = "INSERT INTO password_reset_tokens (user_id, value, expiration_datetime, used) VALUES (?, ?, " +
                "?, ?)";
        jdbcTemplate.update(query, data.getUser().getId(), data.getValue(), data.getExpirationDatetime(),
                data.getUsed());
    }

    List<Map<String, Object>> find(PasswordResetTokensFilter filter) {

        String query = "SELECT * FROM password_reset_tokens";

        if (filter != null) {

            query += " WHERE 1=1";

            if (filter.getValue() != null) {
                query += " AND value = '" + filter.getValue() + "'";
            }

        }

        return jdbcTemplate.queryForList(query);


    }

    void update(PasswordResetTokenData data) {
        String query = "UPDATE password_reset_tokens SET user_id = ?, value = ?, expiration_datetime = ?, used = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, data.getUser().getId(), data.getValue(), data.getExpirationDatetime(), data.getUsed()
                , data.getId());
    }
}
