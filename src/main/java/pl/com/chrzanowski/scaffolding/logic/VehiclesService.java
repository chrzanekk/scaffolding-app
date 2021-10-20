package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getFloat;

@Service
public class VehiclesService implements IVehicle {

    private VehiclesJdbcRepository vehiclesJdbcRepository;
    private IVehicleBrand iVehicleBrand;
    private IVehicleModel iVehicleModel;

    public VehiclesService(VehiclesJdbcRepository vehiclesJdbcRepository,
                           IVehicleBrand iVehicleBrand,
                           IVehicleModel iVehicleModel) {
        this.vehiclesJdbcRepository = vehiclesJdbcRepository;
        this.iVehicleBrand = iVehicleBrand;
        this.iVehicleModel = iVehicleModel;

    }

    public List<VehicleData> find(VehicleFilter filter)  {
        return getVehicles(vehiclesJdbcRepository.find(filter));
    }

    public VehicleData findById(VehicleFilter filter)  {
        return getVehicles(vehiclesJdbcRepository.find(filter)).get(0);
    }


    public Long add(VehicleData data) {
        Long brandId = iVehicleBrand.find(new VehicleBrandFilter(data.getBrandName())).get(0).getId();
        Long modelId = iVehicleModel.find(new VehicleModelFilter(data.getModelName())).get(0).getId();
        return vehiclesJdbcRepository.create(new VehicleData(
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
                data.getHeight()
                ));
    }

    public void update(VehicleData data) {
        vehiclesJdbcRepository.update(
                data);
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
