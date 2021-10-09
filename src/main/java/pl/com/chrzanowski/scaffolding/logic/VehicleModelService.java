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

    private VehiclesModelJdbcRepository vehiclesModelJdbcRepository;

    public VehicleModelService(VehiclesModelJdbcRepository vehiclesModelJdbcRepository) {
        this.vehiclesModelJdbcRepository = vehiclesModelJdbcRepository;
    }

    public List<VehicleModelData> find(VehicleModelFilter filter) {
        return getVehiclesModels(vehiclesModelJdbcRepository.find(filter));
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
