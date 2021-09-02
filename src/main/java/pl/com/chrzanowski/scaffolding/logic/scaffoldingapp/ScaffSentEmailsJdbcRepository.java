package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffSentEmailData;

@Service
public class ScaffSentEmailsJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    public ScaffSentEmailsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void create(ScaffSentEmailData data) {
        String query = "insert into crs_sent_emails (order_id, customer_id, title, content, language, event, create_datetime) values (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, data.getOrderId(), data.getUserId(), data.getTitle(), data.getContent(),
                data.getLanguage(), data.getEvent(), data.getCreateDatetime());
    }
}
