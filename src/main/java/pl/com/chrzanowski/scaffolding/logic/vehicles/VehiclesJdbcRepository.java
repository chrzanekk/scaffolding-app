package pl.com.chrzanowski.scaffolding.logic.vehicles;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.vehicles.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.vehicles.VehicleFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;
import pl.com.chrzanowski.scaffolding.logic.vehiclebrands.VehicleBrandJdbcRepository;
import pl.com.chrzanowski.scaffolding.logic.vehiclemodels.VehicleModelJdbcRepository;

import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.preparePaginationQuery;

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
                data.getBrandId(),
                data.getModelId(),
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


    public void update(VehicleData data) {
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

            if (filter.getBrandId() != null) {
                query += " AND vehicle_brand.id = '" + filter.getBrandId() + "'";
            }

            if (filter.getModelName() != null) {
                query += " AND vehicle_model.name = '" + filter.getModelName() + "'";
            }

            if (filter.getModelId() != null) {
                query += " AND vehicle_model.id = '" + filter.getModelId() + "'";
            }

            if (filter.getPage() != null && filter.getPageSize() != null) {
                query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
            }
        }
        return jdbcTemplate.queryForList(query);
    }

    List<Map<String, Object>> findWithoutTires(VehicleFilter filter) {
        String query = "SELECT \n" +
                "vehicles.id AS id,\n" +
                "vehicles.registration_number,\n" +
                "vehicle_brand.name AS brand,\n" +
                "vehicle_model.name AS model\n" +
                "FROM vehicles \n" +
                "JOIN vehicle_brand ON (vehicles.brand_id = vehicle_brand.id)\n" +
                "JOIN vehicle_model ON (vehicles.model_id = vehicle_model.id)\n" +
                "WHERE vehicles.id NOT IN (SELECT vehicle_id FROM vehicle_tires)";

        if (filter != null) {
            query += " AND 1=1";

            if (filter.getId() != null) {
                query += " AND vehicles.id = '" + filter.getId() + "'";
            }

        }
        return jdbcTemplate.queryForList(query);
    }

}
