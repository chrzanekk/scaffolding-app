package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ScaffVehicleTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffVehicleTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(ScaffVehicleData data) {
        String query = "INSERT INTO vehicle_type (name) VALUES (?);";
        jdbcTemplate.update(query, data.getVehicleType());
        return commonJdbcRepository.getLastInsertedId();
    }

    public List<ScaffVehicleTypeData> find(ScaffVehicleTypeFilter filter) {

        String query = "SELECT * FROM vehicle_type";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }

            if (filter.getName() != null) {
                query += " AND name = " + filter.getName();
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }

        return prepareVehicleTypes(query);
    }

    public Long getVehicleTypeId(ScaffVehicleTypeFilter filter) {

        List<ScaffVehicleTypeData> list = find(filter);

        for (ScaffVehicleTypeData row : list) {
            if (row.getName().equals(filter.getName())) {
                return row.getId();
            }
        }
        return null;
    }

    private List<ScaffVehicleTypeData> prepareVehicleTypes(String query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<ScaffVehicleTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new ScaffVehicleTypeData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }


}
