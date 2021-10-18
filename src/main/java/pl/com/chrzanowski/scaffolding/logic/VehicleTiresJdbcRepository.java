package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.preparePaginationQuery;

@Service
public class VehicleTiresJdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;

    public VehicleTiresJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    List<Map<String, Object>> find (VehicleTiresFilter filter) {
        String query = "SELECT\n" +
                "vehicle_tires.id AS id,\n" +
                "vehicle_tires.vehicle_id AS vehicleId,\n" +
                "vehicle_tires.tire_id AS tireId,\n" +
                "vehicle_tires.`status` AS status,\n" +
                "vehicle_tires.production_year AS productionYear,\n" +
                "vehicle_tires.purchase_date AS purchaseDate,\n" +
                "tires.brand AS brand,\n" +
                "tires.model AS model,\n" +
                "tires.width AS width,\n" +
                "tires.profile AS profile,\n" +
                "tires.diameter AS diameter,\n" +
                "tires.speed_index AS speedIndex,\n" +
                "tires.capacity_index AS capacityIndex,\n" +
                "tires.reinforced AS reinforced,\n" +
                "tires.run_on_flat AS runOnFlat,\n" +
                "tire_season.id AS seasonId,\n" +
                "tire_season.name AS seasonName\n" +
                "FROM vehicle_tires\n" +
                "JOIN tires ON (vehicle_tires.vehicle_id = tires.id)\n" +
                "JOIN tire_season ON (tires.season_id = tire_season.id)";

        if (filter != null) {
            query += " WHERE 1+1";
            if (filter.getId() != null) {
                query += " AND vehicle_tires.id = '" + filter.getId() + "'";
            }
            if (filter.getVehicleId() != null) {
                query += " AND vehicle_tires.vehicle_id = '" + filter.getVehicleId() + "'";
            }

            if (filter.getStatus() != null) {
                query += " AND vehicle_tires.status = '" + filter.getStatus() + "'";
            }

            if (filter.getSeasonName() != null) {
                query += " AND vehicle_season.name = '" + filter.getSeasonName() + "'";
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return jdbcTemplate.queryForList(query);
    }
}
