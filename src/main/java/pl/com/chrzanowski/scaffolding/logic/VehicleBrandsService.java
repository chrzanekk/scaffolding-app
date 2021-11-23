package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;


@Service
public class VehicleBrandsService implements IVehicleBrands {

    private VehicleBrandJdbcRepository vehicleBrandJdbcRepository;
    private DataValidateService dataValidateService;

    public VehicleBrandsService(VehicleBrandJdbcRepository vehicleBrandJdbcRepository,
                                DataValidateService dataValidateService) {
        this.vehicleBrandJdbcRepository = vehicleBrandJdbcRepository;
        this.dataValidateService = dataValidateService;
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
        dataValidateService.validateTextField(data.getName(), "Marka");
    }
}
