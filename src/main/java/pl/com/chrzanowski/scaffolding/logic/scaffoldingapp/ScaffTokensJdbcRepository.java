package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffTokenData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;

import java.util.List;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ScaffTokensJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private ScaffUserJdbcRepository scaffUserJdbcRepository;

    public ScaffTokensJdbcRepository(JdbcTemplate jdbcTemplate, ScaffUserJdbcRepository scaffUserJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.scaffUserJdbcRepository = scaffUserJdbcRepository;
    }

    void create(ScaffTokenData data) {

        Long customerId;
        if (data.getUser() != null) {
            customerId = data.getUser().getId();
        } else {
            customerId = null;
        }

        String query = "INSERT INTO tokens (value, expiration_datetime, user_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, data.getValue(), data.getExpirationDatetime(), customerId);
    }

    ScaffTokenData get(String value) {
        try {
            String query = "SELECT * FROM tokens WHERE value = ?;";
            return jdbcTemplate.queryForObject(query, new Object[]{value},
                    (rs, rowNum) -> {

                        Long userId = getLong(rs, "user_id");
                        ScaffUserData user;
                        if (userId == null) {
                            user = null;
                        } else {
                            List<ScaffUserData> users = scaffUserJdbcRepository.find(new ScaffUsersFilter(userId));
                            if (users.size() == 1) {
                                user = users.get(0);
                            } else {
                                throw new IllegalArgumentException("Found more than one user with ID: " + userId);
                            }
                        }

                        return new ScaffTokenData(
                                getLong(rs, "id"),
                                getString(rs, "value"),
                                getDateTime(rs, "expiration_datetime"),
                                user
                        );
                    });
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("There is no token with value: " + value);
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new IllegalArgumentException("There is more than one token with value: " + value);
        }
    }
}
