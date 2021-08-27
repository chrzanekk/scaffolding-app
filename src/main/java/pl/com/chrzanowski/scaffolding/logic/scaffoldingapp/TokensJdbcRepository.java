package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.TokenData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UsersFilter;
//import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerData;
//import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomersFilter;
//import pl.com.chrzanowski.scaffolding.domain.courseplatform.TokenData;
//import pl.com.chrzanowski.scaffolding.logic.courseplatform.CourseCustomersJdbcRepository;

import java.util.List;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class TokensJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private UserJdbcRepository userJdbcRepository;

    public TokensJdbcRepository(JdbcTemplate jdbcTemplate, UserJdbcRepository userJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userJdbcRepository = userJdbcRepository;
    }

    void create(TokenData data) {

        Long customerId;
        if (data.getUser() != null) {
            customerId = data.getUser().getId();
        } else {
            customerId = null;
        }

        String query = "INSERT INTO tokens (value, expiration_datetime, user_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, data.getValue(), data.getExpirationDatetime(), customerId);
    }

    TokenData get(String value) {
        try {
            String query = "SELECT * FROM tokens WHERE value = ?;";
            return jdbcTemplate.queryForObject(query, new Object[]{value},
                    (rs, rowNum) -> {

                        Long userId = getLong(rs, "user_id");
                        UserData customer;
                        if (userId == null) {
                            customer = null;
                        } else {
                            List<UserData> users = userJdbcRepository.find(new UsersFilter(userId));
                            if (users.size() == 1) {
                                customer = users.get(0);
                            } else {
                                throw new IllegalArgumentException("Found more than one customer with ID: " + userId);
                            }
                        }

                        return new TokenData(
                                getLong(rs, "id"),
                                getString(rs, "value"),
                                getDateTime(rs, "expiration_datetime"),
                                customer
                        );
                    });
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("There is no token with value: " + value);
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new IllegalArgumentException("There is more than one token with value: " + value);
        }
    }
}
