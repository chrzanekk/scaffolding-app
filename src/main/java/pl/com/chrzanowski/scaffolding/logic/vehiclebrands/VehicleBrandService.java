package pl.com.chrzanowski.scaffolding.logic.vehiclebrands;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.logic.IVehicleBrands;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;


@Service
public class VehicleBrandService implements IVehicleBrands {

    private VehicleBrandJdbcRepository vehicleBrandJdbcRepository;

    public VehicleBrandService(VehicleBrandJdbcRepository vehicleBrandJdbcRepository) {
        this.vehicleBrandJdbcRepository = vehicleBrandJdbcRepository;

    }

    public Long add(VehicleBrandData data) {
        validateData(data);
        if(isBrandNameExist(data.getName())) {
            return find(new VehicleBrandFilter(data.getId())).get(0).getId();
        }
        return vehicleBrandJdbcRepository.create(data);
    }

    public void update(VehicleBrandData data) {
        validateData(data);
        vehicleBrandJdbcRepository.update(data);
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

    private boolean isBrandNameExist(String brandName) {
        return !find(new VehicleBrandFilter(brandName)).isEmpty();
    }

    private void validateData(VehicleBrandData data) {
        DataValidationUtil.validateTextField(data.getName(), "Marka");
    }
}
