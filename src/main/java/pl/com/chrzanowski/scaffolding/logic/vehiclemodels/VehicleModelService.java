package pl.com.chrzanowski.scaffolding.logic.vehiclemodels;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.vehiclemodels.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.vehiclemodels.VehicleModelFilter;
import pl.com.chrzanowski.scaffolding.logic.IVehicleModels;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getString;

@Service
public class VehicleModelService implements IVehicleModels {

    private VehicleModelJdbcRepository vehicleModelJdbcRepository;

    public VehicleModelService(VehicleModelJdbcRepository vehicleModelJdbcRepository) {
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
        DataValidationUtil.validateValue(data.getBrandId(),"Marka");
        DataValidationUtil.validateTextField(data.getName(), "Model");
    }
}
