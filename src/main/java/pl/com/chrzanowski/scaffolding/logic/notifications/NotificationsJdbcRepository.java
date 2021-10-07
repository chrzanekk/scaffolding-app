package pl.com.chrzanowski.scaffolding.logic.notifications;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.NotificationsFilter;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.logic.UserService;
import pl.com.chrzanowski.scaffolding.logic.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class NotificationsJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private UserService userService;

    public NotificationsJdbcRepository(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    void create(NotificationData data) {
        String query = "INSERT INTO notifications (create_datetime, seen_datetime, delete_datetime, user_id, " +
                "title, content, link, status, type, kind, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getCreateDatetime(), data.getSeenDatetime(), data.getDeleteDatetime(),
                data.getUser().getId(), data.getTitle(), data.getContent(), data.getLink(), data.getStatus().getCode(),
                data.getType().getCode(), data.getKind().getCode(), data.getLanguage().getCode());
    }

    List<NotificationData> find(NotificationsFilter filter) {

        List<Object> params = new ArrayList<>();

        String query = "SELECT * FROM notifications";

        boolean filterNotNull = filter != null;

        if (filterNotNull) {

            query += " WHERE 1=1";

            if (filter.getDeleted() != null) {
                if (filter.getDeleted()) {
                    query += " AND delete_datetime IS NOT NULL";
                } else {
                    query += " AND delete_datetime IS NULL";
                }
            }

            if (filter.getUser() != null) {
                query += " AND user_id = ?";
                params.add(filter.getUser().getId());
            }

            if (filter.getType() != null) {
                query += " AND type = ?";
                params.add(filter.getType().getCode());
            }
        }

        query += " ORDER BY create_datetime DESC";

        if (filterNotNull && filter.getLimit() != null) {
            query += " LIMIT ?";
            params.add(filter.getLimit());
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, params.toArray());

        List<NotificationData> notifications = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            notifications.add(new NotificationData(
                    getLong(row, "id"),
                    getDateTime(row, "create_datetime"),
                    getDateTime(row, "seen_datetime"),
                    getDateTime(row, "delete_datetime"),
                    prepareCustomer(getLong(row, "user_id")),
                    getString(row, "title"),
                    getString(row, "content"),
                    getString(row, "link"),
                    NotificationStatus.from(getString(row, "status")),
                    NotificationType.from(getString(row, "type")),
                    NotificationKind.from(getString(row, "kind")),
                    Language.from(getString(row, "language"))
            ));
        }
        return notifications;
    }

    void update(NotificationData data) {

        List<Object> params = new ArrayList<>();
        String query = "UPDATE notifications SET";

        query += " create_datetime = ?,";
        params.add(data.getCreateDatetime());

        query += " seen_datetime = ?,";
        params.add(data.getSeenDatetime());

        query += " delete_datetime = ?,";
        params.add(data.getDeleteDatetime());

        query += " user_id = ?,";
        params.add(data.getUser().getId());

        query += " title = ?,";
        params.add(data.getTitle());

        query += " content = ?,";
        params.add(data.getContent());

        query += " link = ?,";
        params.add(data.getLink());

        query += " status = ?,";
        params.add(data.getStatus().getCode());

        query += " type = ?,";
        params.add(data.getType().getCode());

        query += " kind = ?,";
        params.add(data.getKind().getCode());

        query += " language = ?";
        params.add(data.getLanguage().getCode());

        query += " WHERE id = ?;";
        params.add(data.getId());

        jdbcTemplate.update(query, params.toArray());
    }

    Long unseenNotificationsCount(UserData user) {
        String query = "SELECT count(*) count FROM notifications " +
                "WHERE seen_datetime IS NULL " +
                "AND delete_datetime IS NULL " +
                "AND user_id = ? AND type = 'p';";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> rs.getLong("count"), user.getId());
    }

    private UserData prepareCustomer(Long id) {
        if (id == null) {
            return null;
        } else {
            return userService.get(id);
        }
    }
}
