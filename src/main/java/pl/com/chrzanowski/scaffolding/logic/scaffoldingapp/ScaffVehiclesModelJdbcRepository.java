package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleModelFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getString;

@Service
public class ScaffVehiclesModelJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffVehiclesModelJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    List<ScaffVehicleModelData> find(ScaffVehicleModelFilter filter) {
        String query = "SELECT * FROM vehicle_model";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
        }
        return prepareVehiclesModels(jdbcTemplate.queryForList(query));
    }

    private List<ScaffVehicleModelData> prepareVehiclesModels(List<Map<String, Object>> rows) {

        List<ScaffVehicleModelData> models = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            models.add(new ScaffVehicleModelData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return models;
    }
}

