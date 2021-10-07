package pl.com.chrzanowski.scaffolding.logic;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTypeFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

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

    public List<VehicleTypeData> find(VehicleTypeFilter filter) {

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

    public Long getVehicleTypeId(VehicleTypeFilter filter) {

        List<VehicleTypeData> list = find(filter);

        for (VehicleTypeData row : list) {
            if (row.getName().equals(filter.getName())) {
                return row.getId();
            }
        }
        return null;
    }

    private List<VehicleTypeData> prepareVehicleTypes(String query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<VehicleTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new VehicleTypeData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }


}
