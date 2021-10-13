package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class VehiclesJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;
    private VehicleBrandJdbcRepository brandJdbcRepository;
    private VehicleModelJdbcRepository modelJdbcRepository;

    public VehiclesJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository,
                                  VehicleBrandJdbcRepository brandJdbcRepository,
                                  VehicleModelJdbcRepository modelJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
        this.brandJdbcRepository = brandJdbcRepository;
        this.modelJdbcRepository = modelJdbcRepository;
    }

    public Long create(VehicleData data) {
        Long brandId = brandJdbcRepository.create(new VehicleBrandData(data.getBrandName()));
        Long modelId = modelJdbcRepository.create(new VehicleModelData(null, brandId, data.getModelName()));

        String query = "INSERT INTO vehicles (" +
                "brand_id," +
                "model_id," +
                "registration_number," +
                "vin," +
                "production_year," +
                "first_registration_date," +
                "free_places_for_technical_inspections," +
                "fuel_type_id," +
                "vehicle_type_id," +
                "length," +
                "width," +
                "height) VALUES (" +
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
                "?, " +
                "? )";
        jdbcTemplate.update(query,
                brandId,
                modelId,
                data.getRegistrationNumber(),
                data.getVin(),
                data.getProductionYear(),
                data.getFirstRegistrationDate(),
                data.getFreePlacesForTechnicalInspections(),
                data.getFuelTypeId(),
                data.getVehicleTypeId(),
                data.getLength(),
                data.getWidth(),
                data.getHeight());
        return commonJdbcRepository.getLastInsertedId();
    }


    public void update(VehicleData data)  {
        String query = "UPDATE vehicles SET " +
                "registration_number = ?," +
                "vin = ?," +
                "production_year = ?," +
                "first_registration_date = ?," +
                "free_places_for_technical_inspections = ?," +
                "fuel_type_id = ?," +
                "vehicle_type_id = ?," +
                "length = ?," +
                "width = ?," +
                "height = ?," +
                "modify_date = ? WHERE " +
                "id = ?;";
        jdbcTemplate.update(query,
                data.getRegistrationNumber(),
                data.getVin(),
                data.getProductionYear(),
                data.getFirstRegistrationDate(),
                data.getFreePlacesForTechnicalInspections(),
                data.getFuelTypeId(),
                data.getVehicleTypeId(),
                data.getLength(),
                data.getWidth(),
                data.getHeight(),
                data.getModifyDate(),
                data.getId());
    }


    List<Map<String, Object>> find(VehicleFilter filter) {

        String query = "SELECT \n" +
                "vehicles.id, \n" +
                "vehicle_brand.id AS brandId, \n" +
                "vehicle_brand.name AS brand, \n" +
                "vehicle_model.id AS modelId, \n" +
                "vehicle_model.name AS model, \n" +
                "vehicles.registration_number, \n" +
                "vehicles.vin, vehicles.production_year, \n" +
                "vehicles.first_registration_date, \n" +
                "vehicles.free_places_for_technical_inspections, \n" +
                "fuel_type.name AS fuel_type, \n" +
                "vehicle_type.name AS vehicle_type ,\n" +
                "vehicles.length AS length, \n" +
                "vehicles.width AS width, \n" +
                "vehicles.height AS height \n" +
                "FROM vehicles  \n" +
                "JOIN vehicle_brand ON (vehicles.brand_id = vehicle_brand.id)\n" +
                "JOIN vehicle_model ON (vehicles.model_id = vehicle_model.id)\n" +
                "JOIN fuel_type ON (vehicles.fuel_type_id = fuel_type.id)\n" +
                "JOIN vehicle_type ON (vehicles.vehicle_type_id = vehicle_type.id)";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getId() != null) {
                query += " AND vehicles.id = '" + filter.getId() + "'";
            }

            if (filter.getRegistrationNumber() != null) {
                query += " AND vehicles.registration_number = '" + filter.getRegistrationNumber() + "'";
            }

            if (filter.getBrandName() != null) {
                query += " AND vehicle_brand.name = '" + filter.getBrandName() + "'";
            }

            if (filter.getModelName() != null) {
                query += " AND vehicle_model.name = '" + filter.getModelName() + "'";
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return jdbcTemplate.queryForList(query);
    }

}
