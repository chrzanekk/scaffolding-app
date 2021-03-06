package pl.com.chrzanowski.scaffolding.logic.vehicletypes;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.vehicletypes.VehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.vehicletypes.VehicleTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.IVehicleTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getString;

@Service
public class VehicleTypesService implements IVehicleTypes {

    private VehicleTypeJdbcRepository vehicleTypeJdbcRepository;

    public VehicleTypesService(VehicleTypeJdbcRepository vehicleTypeJdbcRepository) {
        this.vehicleTypeJdbcRepository = vehicleTypeJdbcRepository;
    }

    public List<VehicleTypeData> find(VehicleTypeFilter filter) {
        return getVehicleTypes(vehicleTypeJdbcRepository.find(filter));
    }

    private List<VehicleTypeData> getVehicleTypes(List<Map<String, Object>> data) {
        List<VehicleTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new VehicleTypeData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }
}
