package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.SentEmailData;

@Service
public class SentEmailsJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    public SentEmailsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void create(SentEmailData data) {
        String query = "INSERT INTO sent_emails (user_id, title, content, language, event, create_datetime) values (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getUserId(), data.getTitle(), data.getContent(), data.getLanguage(), data.getEvent(), data.getCreateDatetime());
    }
}
