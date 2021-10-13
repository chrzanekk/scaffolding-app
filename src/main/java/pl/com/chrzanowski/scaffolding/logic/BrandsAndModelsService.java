package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class BrandsAndModelsService {
    private VehicleModelJdbcRepository vehicleModelJdbcRepository;
    private VehicleBrandJdbcRepository vehicleBrandJdbcRepository;
    private BrandsAndModelsJdbcRepository brandsAndModelsJdbcRepository;

    public BrandsAndModelsService(VehicleModelJdbcRepository vehicleModelJdbcRepository,
                                  VehicleBrandJdbcRepository vehicleBrandJdbcRepository,
                                  BrandsAndModelsJdbcRepository brandsAndModelsJdbcRepository) {
        this.vehicleModelJdbcRepository = vehicleModelJdbcRepository;
        this.vehicleBrandJdbcRepository = vehicleBrandJdbcRepository;
        this.brandsAndModelsJdbcRepository = brandsAndModelsJdbcRepository;
    }

    public Long addBrand(BrandsAndModelsData data) {
        return vehicleBrandJdbcRepository.create(new VehicleBrandData(data.getBrandName()));
    }

    public Long addModel(BrandsAndModelsData data) {
        return vehicleModelJdbcRepository.create(new VehicleModelData(data.getModelName()), data.getBrandId());
    }

    public void updateBrand(BrandsAndModelsData data) {
        vehicleBrandJdbcRepository.update(new VehicleBrandData(data.getBrandName()),data.getBrandId());
    }

    public void updateModel(BrandsAndModelsData data) {
        vehicleModelJdbcRepository.update(new VehicleModelData(data.getModelName()),data.getModelId());
    }

    public List<BrandsAndModelsData> find(BrandsAndModelsFilter filter) {
        return getBrandsAndModels(brandsAndModelsJdbcRepository.find(filter));
    }




    private List<BrandsAndModelsData> getBrandsAndModels(List<Map<String,Object>> data) {
        List<BrandsAndModelsData> list = new ArrayList<>();
        for (Map<String,Object> row : data) {
            list.add(new BrandsAndModelsData(
                    getLong(row, "brandId"),
                    getLong(row, "modelId"),
                    getString(row,"brandName"),
                    getString(row, "modelName")
            ));
        }
        return list;
    }
}
