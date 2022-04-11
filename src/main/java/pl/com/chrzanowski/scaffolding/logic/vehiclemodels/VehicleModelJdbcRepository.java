package pl.com.chrzanowski.scaffolding.logic.vehiclemodels;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;


import java.util.List;
import java.util.Map;

@Service
public class VehicleModelJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public VehicleModelJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(VehicleModelData data) {
        String query = "INSERT INTO vehicle_model (brand_id, name) VALUES (?,?)";
        jdbcTemplate.update(query, data.getBrandId(), data.getName());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(VehicleModelData data) {
        String query = "UPDATE vehicle_model SET name = ?, brand_id = ?, modify_date = ? WHERE id = ?;";
        jdbcTemplate.update(query, data.getName(), data.getBrandId(),data.getModifyDate(), data.getId());
    }

    List<Map<String, Object>> find(VehicleModelFilter filter) {
        String query = "SELECT * FROM vehicle_model";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND id = '" + filter.getId() + "'";
            }
            if (filter.getBrandId() != null) {
                query += " AND brand_id = '" + filter.getBrandId() + "'";
            }
            if (filter.getName() != null) {
                query += " AND name = '" + filter.getName() + "'";
            }
        }
        return jdbcTemplate.queryForList(query);
    }
}

