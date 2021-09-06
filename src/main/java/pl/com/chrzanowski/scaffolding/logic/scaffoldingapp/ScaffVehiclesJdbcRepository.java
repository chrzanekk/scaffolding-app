package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.*;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.adviser.JdbcUtil.*;

@Service
public class ScaffVehiclesJdbcRepository {
    private JdbcTemplate jdbcTemplate;
    private CommonJdbcRepository commonJdbcRepository;
    private ScaffVehicleBrandJdbcRepository brandJdbcRepository;
    private ScaffVehiclesModelJdbcRepository modelJdbcRepository;

    public ScaffVehiclesJdbcRepository(JdbcTemplate jdbcTemplate, CommonJdbcRepository commonJdbcRepository,
                                       ScaffVehicleBrandJdbcRepository brandJdbcRepository,
                                       ScaffVehiclesModelJdbcRepository modelJdbcRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.commonJdbcRepository = commonJdbcRepository;
        this.brandJdbcRepository = brandJdbcRepository;
        this.modelJdbcRepository = modelJdbcRepository;
    }

    List<ScaffVehicleData> find(ScaffVehiclesFilter filter) throws SQLException {

        String query = "SELECT * FROM vehicles";

        if (filter != null) {
            query += " WHERE 1+1";

            if (filter.getBrandId() != null) {
                query += " AND brand_id = '" + filter.getBrandId() + "'";
            }
            if (filter.getModelId() != null) {
                query += " AND model_id = '" + filter.getModelId() + "'";
            }
            if (filter.getRegistrationNumber() != null) {
                query += " AND registration_number = '" + filter.getRegistrationNumber() + "'";
            }
            query+= preparePaginationQuery(filter.getPage(),filter.getPageSize());
        }
        return prepareVehicles(jdbcTemplate.queryForList(query));
    }

//    List<ScaffVehicleData> findMainList(ScaffVehiclesFilter filter) throws SQLException {
//
//        String query = "SELECT vb.name, vm.name, vh.registration_number" +
//                " FROM vehicles AS vh " +
//                "JOIN vehicle_brand AS vb ON (vh.brand_id = vb.id) " +
//                "JOIN vehicle_model AS vm ON (vh.model_id = vm.id)";
//
////        if (filter != null) {
////            query += preparePaginationQuery(filter.getPage(), filter.getPageSize());
////        }
//        List<Map<String,Object>> rows = jdbcTemplate.queryForList(query);
//        List<ScaffVehicleData> list = new ArrayList<>();
//        for (Map<String, Object> row : rows) {
//            list.add(new ScaffVehicleData(
//                    getString(row, "vb.name"),
//                    getString(row, "vm.name"),
//                    getString(row, "vh.registration_number")
//            ));
//        }
//        return list;
//    }


    private List<ScaffVehicleData> prepareVehicles(List<Map<String, Object>> rows) throws SQLException {

        List<ScaffVehicleData> vehicles = new ArrayList<>();

        for (Map<String, Object> row : rows) {

            Long brandId = getLong(row, "brand_id");

            ScaffVehicleBrandData brand;
            if (brandId == null) {
                brand = null;
            } else {
                brand = brandJdbcRepository.find(new ScaffVehicleBrandFilter(brandId)).get(0);
            }

            Long modelId = getLong(row, "model_id");

            ScaffVehicleModelData model;
            if (modelId == null) {
                model = null;
            } else {
                model = modelJdbcRepository.find(new ScaffVehicleModelFilter(modelId)).get(0);
            }

            vehicles.add(new ScaffVehicleData(
                    getLong(row, "id"),
                    brand,
                    model,
                    getString(row, "registration_number"),
                    getString(row, "vin"),
                    getInteger(row, "production_year"),
                    getDate(row, "first_registration_date"),
                    getInteger(row, "free_places_for_technical_inspection"),
                    getLong(row, "fuel_type_id"),
                    getLong(row, "vehicle_type_id")
            ));
        }
        return vehicles;
    }
}
