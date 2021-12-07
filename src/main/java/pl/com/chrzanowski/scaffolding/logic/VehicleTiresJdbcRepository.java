package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.math.BigDecimal;
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

    public Long create(VehicleTiresData data) {

        String query = "INSERT INTO vehicle_tires (" +
                "vehicle_id, " +
                "tire_id, " +
                "status, " +
                "production_year, " +
                "purchase_date) VALUES ( " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?)";
        jdbcTemplate.update(query,
                data.getVehicleId(),
                createTire(data),
                data.getStatus(),
                data.getProductionYear(),
                data.getPurchaseDate());

        return commonJdbcRepository.getLastInsertedId();
    }

    public void updateTire(VehicleTiresData data) {
        String query = "UPDATE vehicle_tires SET " +
                "vehicle_id = ?, " +
                "tire_id = ?, " +
                "status = ?, " +
                "production_year = ?, " +
                "purchase_date = ?," +
                "modify_date =? WHERE " +
                "id = ?";
        jdbcTemplate.update(query,
                data.getVehicleId(),
                data.getTireId(),
                data.getStatus(),
                data.getProductionYear(),
                data.getPurchaseDate(),
                data.getModifyDate(),
                data.getId());
    }

    public void updateTireProperties(VehicleTiresData data) {
        String query = "UPDATE tires SET " +
                "brand = ?," +
                "model = ?," +
                "width = ?," +
                "profile = ?," +
                "diameter = ?," +
                "type = ?," +
                "speed_index = ?," +
                "capacity_index = ?," +
                "reinforced = ?," +
                "run_on_flat = ?, " +
                "season_id = ?," +
                "modify_date = ? WHERE " +
                "id = ?;";

        jdbcTemplate.update(query,
                data.getBrand(),
                data.getModel(),
                data.getWidth(),
                data.getProfile(),
                data.getDiameter(),
                data.getType(),
                data.getSpeedIndex(),
                data.getCapacityIndex(),
                data.getReinforced(),
                data.isRunOnFlat(),
                data.getSeasonId(),
                data.getModifyDate(),
                data.getTireId());
    }

    List<Map<String, Object>> find(VehicleTiresFilter filter) {
        String query = "SELECT\n" +
                "vehicle_tires.id AS id,\n" +
                "vehicle_tires.vehicle_id AS vehicleId,\n" +
                "vehicle_tires.tire_id AS tireId,\n" +
                "vehicle_tires.`status` AS status,\n" +
                "vehicle_tires.production_year AS productionYear,\n" +
                "vehicle_tires.purchase_date AS purchaseDate,\n" +
                "tires.id AS tireId,\n" +
                "tires.brand AS brand,\n" +
                "tires.model AS model,\n" +
                "tires.width AS width,\n" +
                "tires.profile AS profile,\n" +
                "tires.diameter AS diameter,\n" +
                "tires.type AS type,\n" +
                "tires.speed_index AS speedIndex,\n" +
                "tires.capacity_index AS capacityIndex,\n" +
                "tires.reinforced AS reinforced,\n" +
                "tires.run_on_flat AS runOnFlat,\n" +
                "tire_season.id AS seasonId,\n" +
                "tire_season.name AS seasonName\n" +
                "FROM vehicle_tires\n" +
                "JOIN tires ON (vehicle_tires.tire_id = tires.id)\n" +
                "JOIN tire_season ON (tires.season_id = tire_season.id)";

        if (filter != null) {
            query += " WHERE 1=1";
            if (filter.getId() != null) {
                query += " AND vehicle_tires.id = '" + filter.getId() + "'";
            }
            if (filter.getVehicleId() != null) {
                query += " AND vehicle_tires.vehicle_id = '" + filter.getVehicleId() + "'";
            }

            if (filter.getStatus() != null) {
                query += " AND vehicle_tires.status = '" + filter.getStatus() + "'";
            }

            if (filter.getBrand() != null) {
                query += " AND tires.brand = '" + filter.getBrand() + "'";
            }

            if (filter.getModel() != null) {
                query += " AND tires.model = '" + filter.getModel() + "'";
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

    public BigDecimal getMountedTireStatus(VehicleTiresFilter filter) {
        String query = prepareQueryForTireStatus(filter, "m");
        Long result = jdbcTemplate.queryForObject(query, (rs, rowNum) -> Long.valueOf(rs.getString("count")));
        return BigDecimal.valueOf(result);
    }

    private Long createTire(VehicleTiresData data) {
        String query = "INSERT INTO tires (" +
                "brand," +
                "model," +
                "width," +
                "profile," +
                "diameter," +
                "type," +
                "speed_index," +
                "capacity_index," +
                "reinforced," +
                "run_on_flat, " +
                "season_id) VALUES (" +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?)";
        jdbcTemplate.update(query,
                data.getBrand(),
                data.getModel(),
                data.getWidth(),
                data.getProfile(),
                data.getDiameter(),
                data.getType(),
                data.getSpeedIndex(),
                data.getCapacityIndex(),
                data.getReinforced(),
                data.isRunOnFlat(),
                data.getSeasonId());

        return commonJdbcRepository.getLastInsertedId();
    }

    private String prepareQueryForTireStatus(VehicleTiresFilter filter, String status) {
        String query = "SELECT COUNT(*) AS count FROM vehicle_tires";

        if (filter != null) {
            query += " WHERE 1=1";
            if (filter.getId() != null) {
                query += " AND vehicle_tires.vehicle_id = '" + filter.getVehicleId() + "'";
            }
            if (filter.getVehicleId() != null) {
                query += " AND vehicle_tires.status = '" + status + "'";
            }
        }
        return query;
    }


}
