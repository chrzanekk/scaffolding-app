package pl.com.chrzanowski.scaffolding.logic.vehicletypes;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.vehicles.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.vehicletypes.VehicleTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class VehicleTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public VehicleTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(VehicleData data) {
        String query = "INSERT INTO vehicle_type (name) VALUES (?);";
        jdbcTemplate.update(query, data.getVehicleType());
        return commonJdbcRepository.getLastInsertedId();
    }

    public List<Map<String, Object>> find(VehicleTypeFilter filter) {

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

        return jdbcTemplate.queryForList(query);
    }



}
