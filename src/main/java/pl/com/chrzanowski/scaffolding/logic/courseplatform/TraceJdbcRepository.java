package pl.com.chrzanowski.scaffolding.logic.courseplatform;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.TraceData;

@Service
public class TraceJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    public TraceJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    void create(TraceData data) {
        String query = "INSERT INTO crs_action_trace (what, value, who, creation_datetime, ip_address, browser, operating_system) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, data.getWhat(), data.getValue(), data.getWho(), data.getLocalDateTime(), data.getIpAddress(), data.getBrowser(), data.getOperatingSystem());
    }
}
