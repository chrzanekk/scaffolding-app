package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;


@Service
public class VehicleBrandService implements IVehicleBrand {

    private VehicleBrandJdbcRepository vehicleBrandJdbcRepository;

    public VehicleBrandService(VehicleBrandJdbcRepository vehicleBrandJdbcRepository) {
        this.vehicleBrandJdbcRepository = vehicleBrandJdbcRepository;
    }

    public List<VehicleBrandData>find(VehicleBrandFilter filter) {
        return getVehiclesBrands(vehicleBrandJdbcRepository.find(filter));
    }

    private List<VehicleBrandData> getVehiclesBrands(List<Map<String, Object>> data) {

        List<VehicleBrandData> brands = new ArrayList<>();

        for (Map<String, Object> row : data) {
            brands.add(new VehicleBrandData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return brands;
    }
}
