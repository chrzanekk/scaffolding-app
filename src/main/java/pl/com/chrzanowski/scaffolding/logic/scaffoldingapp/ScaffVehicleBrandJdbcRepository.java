package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.getString;

@Service
public class ScaffVehicleBrandJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public ScaffVehicleBrandJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    List<ScaffVehicleBrandData> find(ScaffVehicleBrandFilter filter) {

        String query = "SELECT * FROM vehicle_brand";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
        }
        return prepareVehiclesBrands(jdbcTemplate.queryForList(query));
    }

    private List<ScaffVehicleBrandData> prepareVehiclesBrands(List<Map<String, Object>> rows) {

        List<ScaffVehicleBrandData> brands = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            brands.add(new ScaffVehicleBrandData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return brands;
    }
}
