package pl.com.chrzanowski.scaffolding.logic.workshops;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopServiceTypeData;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopServiceTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

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
        Long[] actionTypes = data.getServiceActionTypes();
        for (Long actionType : actionTypes) {
            jdbcTemplate.update(query, data.getWorkshopId(), actionType);
        }
    }

    void deleteActionsFromWorkshop(WorkshopServiceTypeFilter filter) {
        String query = "DELETE FROM workshops_service_types WHERE workshop_id = " + filter.getWorkshopId();
        jdbcTemplate.update(query);
    }

    List<Map<String, Object>> find(WorkshopServiceTypeFilter filter) {
        String query = "SELECT * FROM workshops_service_types JOIN " +
                "service_action_type ON (workshops_service_types.service_action_type_id = service_action_type.id)";

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
