package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleFilter;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ScaffVehiclesJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;
    private ScaffVehicleBrandJdbcRepository brandJdbcRepository;
    private ScaffVehiclesModelJdbcRepository modelJdbcRepository;
    private ScaffFuelTypeJdbcRepository fuelTypeJdbcRepository;
    private ScaffVehicleTypeJdbcRepository vehicleTypeJdbcRepository;

    public ScaffVehiclesJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository,
                                       ScaffVehicleBrandJdbcRepository brandJdbcRepository,
                                       ScaffVehiclesModelJdbcRepository modelJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
        this.brandJdbcRepository = brandJdbcRepository;
        this.modelJdbcRepository = modelJdbcRepository;
    }

    public Long create(ScaffVehicleData data) {
        Long brandId = brandJdbcRepository.create(data);
        Long modelId = modelJdbcRepository.create(data, brandId);

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


    public void update(ScaffVehicleData data) throws SQLException {
        Long brandId = find(new ScaffVehicleFilter(data.getId())).get(0).getBrandId();
        Long modelId = find(new ScaffVehicleFilter(data.getId())).get(0).getModelId();
        brandJdbcRepository.update(data, brandId);
        modelJdbcRepository.update(data, modelId);

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


    List<ScaffVehicleData> find(ScaffVehicleFilter filter) throws SQLException {

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


        return prepareVehicles(query);

    }

    ScaffVehicleData get(ScaffVehicleFilter filter) throws SQLException {
        return getVehicle(find(filter), filter);
    }

    private List<ScaffVehicleData> prepareVehicles(String query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<ScaffVehicleData> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            list.add(new ScaffVehicleData(
                    getLong(row, "id"),
                    getLong(row, "brandId"),
                    getString(row, "brand"),
                    getLong(row, "modelId"),
                    getString(row, "model"),
                    getString(row, "registration_number"),
                    getString(row, "vin"),
                    getInteger(row, "production_year"),
                    getDate(row, "first_registration_date"),
                    getInteger(row, "free_places_for_technical_inspections"),
                    getString(row, "fuel_type"),
                    getString(row, "vehicle_type"),
                    getFloat(row,"length"),
                    getFloat(row,"width"),
                    getFloat(row,"height")
            ));
        }
        return list;
    }

    private ScaffVehicleData getVehicle(List<ScaffVehicleData> list, ScaffVehicleFilter filter) {

        ScaffVehicleData vehicle = null;

        for (ScaffVehicleData data : list) {
            if (filter.getId().equals(data.getId())) {
                vehicle = data;
                break;
            }
        }
        return vehicle;
    }
}
