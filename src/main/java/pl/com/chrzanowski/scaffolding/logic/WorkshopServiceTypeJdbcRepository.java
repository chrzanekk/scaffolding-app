package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeFilter;

import java.util.List;
import java.util.Map;

@Service
public class WorkshopServiceTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public WorkshopServiceTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    void create(WorkshopServiceTypeData data) {
        String query = "INSERT INTO workshops_service_types (workshop_id, service_action_type_id) VALUES (?, ?)";
        for (int i = 0; i < data.getServiceActionTypes().length; i++) {
            jdbcTemplate.update(query, data.getWorkshopId(), data.getServiceActionTypeId());
        }
    }

    void deleteActionsFromWorkshop(WorkshopServiceTypeFilter filter) {
        String query = "DELETE FROM workshops_service_types WHERE workshop_id = " + filter.getWorkshopId();
        jdbcTemplate.update(query);
    }

    List<Map<String, Object>> find(WorkshopServiceTypeFilter filter) {
        String query = "SELECT * FROM workshops_service_types";

        if (filter != null) {
            query += " WHERE 1=1";

            if (filter.getServiceActionTypeId() != null) {
                query += " AND service_action_type_id = '" + filter.getServiceActionTypeId() + "'";
            }

            if (filter.getWorkshopId() != null) {
                query += " AND workshop_id = '" + filter.getWorkshopId() + "'";
            }
        }

        return jdbcTemplate.queryForList(query);
    }
}
