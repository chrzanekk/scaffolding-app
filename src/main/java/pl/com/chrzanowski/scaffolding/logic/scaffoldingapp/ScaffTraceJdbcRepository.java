package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffTraceData;

@Service
public class ScaffTraceJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    public ScaffTraceJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    void create(ScaffTraceData data) {
        String query = "INSERT INTO action_trace (what, value, who, creation_datetime, ip_address, browser, operating_system) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, data.getWhat(), data.getValue(), data.getWho(), data.getLocalDateTime(), data.getIpAddress(), data.getBrowser(), data.getOperatingSystem());
    }
}
