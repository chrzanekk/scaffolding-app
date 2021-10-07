package pl.com.chrzanowski.scaffolding.logic;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class VehiclesModelJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public VehiclesModelJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(VehicleData data, Long id) {
        String query = "INSERT INTO vehicle_model (id,name) VALUES (?,?)";
        jdbcTemplate.update(query, id, data.getModelName());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(VehicleData data, Long id) {
        String query = "UPDATE vehicle_model SET name = ?, modify_date = ? WHERE id = ?;";
        jdbcTemplate.update(query, data.getModelName(), data.getModifyDate(), id);
    }

    List<VehicleModelData> find(VehicleModelFilter filter) {
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

    public VehicleModelData findByName(String name) {
        List<VehicleModelData> models = find(new VehicleModelFilter(name));
        if (models.size() == 0) {
            throw new IllegalArgumentException("Models not found");
        } else {
            return models.get(0);
        }
    }

    private List<VehicleModelData> prepareVehiclesModels(List<Map<String, Object>> rows) {

        List<VehicleModelData> models = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            models.add(new VehicleModelData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return models;
    }
}

