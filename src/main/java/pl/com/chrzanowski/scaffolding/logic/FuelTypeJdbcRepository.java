package pl.com.chrzanowski.scaffolding.logic;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class FuelTypeJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public FuelTypeJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public Long create(VehicleData data) {
        String query = "INSERT INTO fuel_type (name) VALUES (?)";
        jdbcTemplate.update(query,data.getFuelType());
        return commonJdbcRepository.getLastInsertedId();
    }

    public List<Map<String,Object>> find(FuelTypeFilter filter) {

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

        return jdbcTemplate.queryForList(query);
    }

}
