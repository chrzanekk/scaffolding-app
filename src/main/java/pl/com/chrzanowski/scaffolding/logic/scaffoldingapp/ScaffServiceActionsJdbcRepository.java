package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class ScaffServiceActionsJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffServiceActionsJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    List<ScaffServiceActionsData> find(ScaffServiceActionsFilter filter) {

        String query = "SELECT \n" +
                "service_actions.id,\n" +
                "service_actions.vehicle_id,\n" +
                "service_actions.car_mileage,\n" +
                "service_actions.service_date,\n" +
                "service_actions.invoice_no,\n" +
                "service_actions.service_workshop,\n" +
                "service_action_type.name AS action_type,\n" +
                "service_action_type.description AS description\n" +
                "FROM service_actions \n" +
                "JOIN service_action_type ON (service_actions.service_action_type_id = service_action_type.id)";

        if (filter != null) {
            query += " WHERE 1+1";
            if (filter.getId() != null) {
                query += " AND service_actions.id = '" + filter.getId() + "'";
            }
            if (filter.getVehicleId() != null) {
                query += " AND service_actions.vehicle_id = '" + filter.getVehicleId() + "'";
            }
            if (filter.getServiceDate() != null) {
                query += " AND service_actions.service_date = '" + filter.getServiceDate() + "'";
            }
            if (filter.getActionType() != null) {
                query += " AND service_action_type.name = '" + filter.getActionType() + "'";
            }
            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return prepareActions(query);
    }

    ScaffServiceActionsData get(ScaffServiceActionsFilter filter) {
        return getAction(find(filter), filter.getId());
    }

    private List<ScaffServiceActionsData> prepareActions(String query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<ScaffServiceActionsData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new ScaffServiceActionsData(
                    getLong(row, "id"),
                    getLong(row, "vehicle_id"),
                    getInteger(row, "car_mileage"),
                    getDate(row, "service_date"),
                    getString(row, "invoice_no"),
                    getString(row, "service_workshop"),
                    getString(row, "action_type"),
                    getString(row, "description")
            ));
        }
        return list;
    }

    private ScaffServiceActionsData getAction(List<ScaffServiceActionsData> list, Long id) {

        ScaffServiceActionsData action = null;

        for (ScaffServiceActionsData data : list) {
            if (id.equals(data.getId())) {
                action = data;
                break;
            }
        }
        return action;
    }

}
