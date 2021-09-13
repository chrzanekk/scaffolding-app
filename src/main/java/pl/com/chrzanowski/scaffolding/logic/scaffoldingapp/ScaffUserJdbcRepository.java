package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class ScaffUserJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffUserJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    Long create(ScaffUserData data) {
        String query = "INSERT INTO users (" +
                "login, " +
                "password_hash, " +
                "first_name, " +
                "second_name, " +
                "language, " +
                "regulation_accepted, " +
                "newsletter_accepted, " +
                "is_enabled, " +
                "registration_datetime, " +
                "registration_ip, " +
                "registration_user_agent, " +
                "email_confirmed" +
                ") " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query,
                data.getLogin(),
                data.getPasswordHash(),
                data.getFirstName(),
                data.getSecondName(),
                data.getLanguage(),
                data.getRegulationAccepted(),
                data.getNewsletterAccepted(),
                data.getEnabled(),
                data.getRegistrationDatetime(),
                data.getRegistrationIp(),
                data.getRegistrationUserAgent(),
                data.getEmailConfirmed());
        return commonJdbcRepository.getLastInsertedId();
    }

    void update(ScaffUserData data) {

        String query = "UPDATE users SET " +
                "login=?, " +
                "password_hash=?, " +
                "first_name=?," +
                "second_name=?," +
                "language=?, " +
                "regulation_accepted=?, " +
                "newsletter_accepted=?, " +
                "is_enabled=?, " +
                "registration_datetime=?, " +
                "registration_ip=?, " +
                "registration_user_agent=?, " +
                "email_confirmed=?, " +
                "delete_datetime=? " +
                "WHERE id=?;";
        jdbcTemplate.update(query,
                data.getLogin(),
                data.getPasswordHash(),
                data.getFirstName(),
                data.getSecondName(),
                data.getLanguage(),
                data.getRegulationAccepted(),
                data.getNewsletterAccepted(),
                data.getEnabled(),
                data.getRegistrationDatetime(),
                data.getRegistrationIp(),
                data.getRegistrationUserAgent(),
                data.getEmailConfirmed(),
                data.getDeleteDateTime(),
                data.getId());
    }

    List<ScaffUserData> find(ScaffUsersFilter filter) {

        String query = "SELECT * FROM users";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getLogin() != null) {
                query += " AND login = '" + filter.getLogin() + "'";
            }

            if (filter.getLoginLike() != null) {
                query += " and login like '%" + filter.getLoginLike() + "%'";
            }

            if (filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }

            if (filter.getEnabled() != null) {
                query += " AND is_enabled = " + (filter.getEnabled() ? "1" : "0");
            }

            if (filter.getNewsletterAccepted() != null) {
                query += " AND newsletter_accepted = " + (filter.getNewsletterAccepted() ? "1" : "0");
            }

            if (filter.getEmailConfirmed() != null) {
                query += " AND email_confirmed = " + (filter.getEmailConfirmed() ? "1" : "0");
            }

            if (filter.getLanguage() != null) {
                query += " AND language = '" + filter.getLanguage().getCode() + "'";
            }

            if (filter.getLimit() != null) {
                query += " LIMIT " + filter.getLimit();
            }

            if (filter.getId() == null) {
                query += " AND delete_datetime is null";
            }

            query += preparePaginationQuery(filter.getPage(), filter.getPageSize());

        }
        return prepareUsers(jdbcTemplate.queryForList(query));
    }

    List<ScaffUserData> findConfirmEmailNotificationRecipients() {
        String query = "SELECT * FROM users " +
                "WHERE is_enabled = true " +
                "AND email_confirmed = false " +
                "AND NOT EXISTS(" +
                "SELECT * FROM notifications " +
                "WHERE user_id = users.id AND seen_datetime IS NULL " +
                "AND kind = 'e');";
        return prepareUsers(jdbcTemplate.queryForList(query));
    }


    private List<ScaffUserData> prepareUsers(List<Map<String, Object>> rows) {

        List<ScaffUserData> users = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            users.add(new ScaffUserData(
                    getLong(row, "id"),
                    getString(row, "login"),
                    getString(row, "password_hash"),
                    getString(row, "language"),
                    getBoolean(row, "regulation_accepted"),
                    getBoolean(row, "newsletter_accepted"),
                    getBoolean(row, "is_enabled"),
                    getDateTime(row, "registration_datetime"),
                    getString(row, "registration_ip"),
                    getString(row, "registration_user_agent"),
                    getBoolean(row, "email_confirmed")
            ));
        }
        return users;
    }
}
