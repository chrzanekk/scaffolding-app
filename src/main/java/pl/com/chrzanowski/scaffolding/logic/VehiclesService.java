package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getFloat;

@Service
public class VehiclesService implements IVehicle {

    private VehiclesJdbcRepository vehiclesJdbcRepository;

    public VehiclesService(VehiclesJdbcRepository vehiclesJdbcRepository) {
        this.vehiclesJdbcRepository = vehiclesJdbcRepository;

    }

    public List<VehicleData> find(VehicleFilter filter)  {
        return getVehicles(vehiclesJdbcRepository.find(filter));
    }

    public VehicleData findById(VehicleFilter filter)  {
        return getVehicles(vehiclesJdbcRepository.find(filter)).get(0);
    }


    public Long add(VehicleData data) {
        return vehiclesJdbcRepository.create(data);
    }

    public void update(VehicleData data) {
        vehiclesJdbcRepository.update(
                data,
                findById(new VehicleFilter(data.getId())).getBrandId(),
                findById(new VehicleFilter(data.getId())).getModelId());
    }

    private List<VehicleData> getVehicles(List<Map<String, Object>> data) {
        List<VehicleData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new VehicleData(
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

}
