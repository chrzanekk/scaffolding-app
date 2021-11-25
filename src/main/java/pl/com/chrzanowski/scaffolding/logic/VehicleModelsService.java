package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;

@Service
public class VehicleModelsService implements IVehicleModels {

    private VehicleModelJdbcRepository vehicleModelJdbcRepository;

    public VehicleModelsService(VehicleModelJdbcRepository vehicleModelJdbcRepository) {
        this.vehicleModelJdbcRepository = vehicleModelJdbcRepository;
    }

    public Long add(VehicleModelData data) {
        validateData(data);
        return vehicleModelJdbcRepository.create(data);
    }

    public void update(VehicleModelData data) {
        validateData(data);
        vehicleModelJdbcRepository.update(data);
    }

    public List<VehicleModelData> find(VehicleModelFilter filter) {
        return getVehiclesModels(vehicleModelJdbcRepository.find(filter));
    }

    private List<VehicleModelData> getVehiclesModels(List<Map<String, Object>> rows) {

        List<VehicleModelData> models = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            models.add(new VehicleModelData(
                    getLong(row, "id"),
                    getLong(row,"brand_id"),
                    getString(row, "name")
            ));
        }
        return models;
    }

    private void validateData(VehicleModelData data) {
        DataValidationUtil.validateTextField(data.getName(), "Model");
    }
}
