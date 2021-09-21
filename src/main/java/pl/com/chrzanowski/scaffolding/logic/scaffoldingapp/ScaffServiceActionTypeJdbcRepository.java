package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsData;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class ScaffServiceActionTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffServiceActionTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(ScaffServiceActionsData data) {
        String query = "INSERT INTO service_action_type (name, description) VALUES (?, ?);";
        jdbcTemplate.update(query, data.getServiceActionName(), data.getServiceActionDescription());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(ScaffServiceActionsData data, Long id) {
        String query = "UPDATE service_action_type SET name = ?, description = ? WHERE id = ?;";
        jdbcTemplate.update(query, data.getServiceActionName(), data.getServiceActionDescription(), id);
    }

    public List<ScaffServiceActionTypeData> find(ScaffServiceActionTypeFilter filter) {

        String query = "SELECT * FROM service_action_type";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }
            if (filter.getName() != null) {
                query += " AND name = " + filter.getName();
            }
            if (filter.getDescription() != null) {
                query += " AND description = " + filter.getDescription();
            }
            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return prepareActionTypes(query);
    }

    private List<ScaffServiceActionTypeData> prepareActionTypes(String query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<ScaffServiceActionTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new ScaffServiceActionTypeData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getString(row, "description")
            ));
        }
        return list;
    }
}