package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getFloat;

@Service
public class VehiclesService implements IVehicles {

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
        return vehiclesJdbcRepository.create(new VehicleData(
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
                data.getHeight()
                ));
    }

    public void update(VehicleData data) {
        vehiclesJdbcRepository.update(
                data);
    }

    public List<VehicleData> findWithoutTires(VehicleFilter filter) {
        return getVehicles(vehiclesJdbcRepository.findWithoutTires(filter));
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

    private void validateVehicleTires(VehicleFilter filter) {
        if(!findWithoutTires(filter).isEmpty()){
            throw new IllegalArgumentException("Samochód nie może jeździć bez kół! Dodaj zestaw opon");
        }
    }
}
