package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class VehicleBrandJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public VehicleBrandJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(VehicleBrandData data) {
        String query = "INSERT INTO vehicle_brand (name) VALUES (?)";
        jdbcTemplate.update(query,data.getName());
        return commonJdbcRepository.getLastInsertedId();
    }

    public void update(VehicleBrandData data) {
        String query = "UPDATE vehicle_brand SET name = ?, modify_date = ? WHERE id = ?;";
        jdbcTemplate.update(query,data.getName(), data.getModifyDate() ,data.getId());
    }

    List<Map<String, Object>> find(VehicleBrandFilter filter) {

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
        return jdbcTemplate.queryForList(query);
    }




}