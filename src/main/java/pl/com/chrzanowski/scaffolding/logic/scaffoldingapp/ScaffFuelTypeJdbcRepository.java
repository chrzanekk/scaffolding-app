package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ScaffFuelTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffFuelTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(ScaffVehicleData data) {
        String query = "INSERT INTO fuel_type (name) VALUES (?)";
        jdbcTemplate.update(query,data.getFuelType());
        return commonJdbcRepository.getLastInsertedId();
    }

    public List<ScaffFuelTypeData> find(ScaffFuelTypeFilter filter) {

        String query = "SELECT * FROM fuel_type";

        if(filter != null) {
            query += " WHERE 1+1";

            if(filter.getId() != null) {
                query += " AND id = " + filter.getId();
            }

            if(filter.getName() != null) {
                query += " AND name = " + filter.getName();
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }

        return prepareFuelTypes(query);
    }

    public Long getFuelTypeId(ScaffFuelTypeFilter filter) {

        List<ScaffFuelTypeData> list = find(filter);

        for(ScaffFuelTypeData row : list) {
            if(row.getName().equals(filter.getName())) {
                return row.getId();
            }
        }
        return null;
    }

    private List<ScaffFuelTypeData> prepareFuelTypes(String query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<ScaffFuelTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new ScaffFuelTypeData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }
}
