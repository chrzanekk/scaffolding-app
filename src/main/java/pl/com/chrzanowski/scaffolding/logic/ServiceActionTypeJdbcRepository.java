package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ServiceActionTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ServiceActionTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(ServiceActionTypeData data) {
        String query = "INSERT INTO service_action_type (name) VALUES (?);";
        jdbcTemplate.update(query, data.getName());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(ServiceActionTypeData data) {
        String query = "UPDATE service_action_type SET name = ?, modify_date = ? WHERE id = ?;";
        jdbcTemplate.update(query, data.getName(),data.getModifyDate(), data.getId());
    }

    public void remove(ServiceActionTypeData data) {
            String query = "UPDATE service_action_type SET name = ?, remove_date = ? WHERE id = ?;";
            jdbcTemplate.update(query, data.getName(), data.getRemoveDate(), data.getId());
    }

    public List<Map<String, Object>> find(ServiceActionTypesFilter filter) {

        String query = "SELECT * FROM service_action_type";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }
            if (filter.getName() != null) {
                query += " AND name = " + filter.getName();
            }

            query += " AND remove_date IS NULL ";

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return jdbcTemplate.queryForList(query);
    }

}
