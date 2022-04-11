package pl.com.chrzanowski.scaffolding.logic.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class UserJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    Long create(UserData data) {
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
                data.getLastName(),
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

    void update(UserData data) {

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
                data.getLastName(),
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

    List<Map<String, Object>> find(UsersFilter filter) {

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
                query += " AND is_enabled = '" + (filter.getEnabled() ? "1" : "0") + "'";
            }

            if (filter.getNewsletterAccepted() != null) {
                query += " AND newsletter_accepted = '" + (filter.getNewsletterAccepted() ? "1" : "0") + "'";
            }

            if (filter.getEmailConfirmed() != null) {
                query += " AND email_confirmed = '" + (filter.getEmailConfirmed() ? "1" : "0") + "'";
            }

            if (filter.getLanguage() != null) {
                query += " AND language = '" + filter.getLanguage().getCode() + "'";
            }

            if (filter.getDeleteDateTime() == null) {
                query += " AND delete_datetime is null";
            }

            if (filter.getLimit() != null) {
                query += " LIMIT " + filter.getLimit();
            }


            query += preparePaginationQuery(filter.getPage(), filter.getPageSize());

        }
        return jdbcTemplate.queryForList(query);
    }

    List<Map<String, Object>>findConfirmEmailNotificationRecipients() {
        String query = "SELECT * FROM users " +
                "WHERE is_enabled = true " +
                "AND email_confirmed = false " +
                "AND NOT EXISTS(" +
                "SELECT * FROM notifications " +
                "WHERE user_id = users.id AND seen_datetime IS NULL " +
                "AND kind = 'e');";
        return jdbcTemplate.queryForList(query);
    }

}
