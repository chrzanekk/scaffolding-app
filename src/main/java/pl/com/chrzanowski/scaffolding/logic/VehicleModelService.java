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
public class VehicleModelService implements IVehicleModel{

    private VehicleModelJdbcRepository vehicleModelJdbcRepository;

    public VehicleModelService(VehicleModelJdbcRepository vehicleModelJdbcRepository) {
        this.vehicleModelJdbcRepository = vehicleModelJdbcRepository;
    }

    public Long add(VehicleModelData data) {
        if(ValidateDataUtil.isModelNameExist(data.getName())){
            return find(new VehicleModelFilter(data.getId())).get(0).getId();
        }
        return vehicleModelJdbcRepository.create(data, data.getBrandId());
    }

    public void update(VehicleModelData data) {
        vehicleModelJdbcRepository.update(data,data.getId());
    }

    public List<VehicleModelData> find(VehicleModelFilter filter) {
        return getVehiclesModels(vehicleModelJdbcRepository.find(filter));
    }

    private List<VehicleModelData> getVehiclesModels(List<Map<String, Object>> rows) {

        List<VehicleModelData> models = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            models.add(new VehicleModelData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return models;
    }
}
