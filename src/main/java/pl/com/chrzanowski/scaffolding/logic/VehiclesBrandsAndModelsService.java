package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;

@Service
public class VehiclesBrandsAndModelsService {
    private VehiclesBrandsAndModelsJdbcRepository vehiclesBrandsAndModelsJdbcRepository;
    private VehicleBrandJdbcRepository brandJdbcRepository;
    private VehicleModelJdbcRepository modelJdbcRepository;
    private IVehicleBrand iVehicleBrand;
    private IVehicleModel iVehicleModel;

    public VehiclesBrandsAndModelsService(VehiclesBrandsAndModelsJdbcRepository vehiclesBrandsAndModelsJdbcRepository,
                                          VehicleBrandJdbcRepository brandJdbcRepository,
                                          VehicleModelJdbcRepository modelJdbcRepository,
                                          IVehicleBrand iVehicleBrand,
                                          IVehicleModel iVehicleModel) {

        this.vehiclesBrandsAndModelsJdbcRepository = vehiclesBrandsAndModelsJdbcRepository;
        this.brandJdbcRepository = brandJdbcRepository;
        this.modelJdbcRepository = modelJdbcRepository;
        this.iVehicleBrand = iVehicleBrand;
        this.iVehicleModel = iVehicleModel;
    }


    public List<VehiclesBrandsAndModelsData> find(VehiclesBrandsAndModelsFilter filter) {
        return getBrandsAndModels(vehiclesBrandsAndModelsJdbcRepository.find(filter));
    }

    public void addBrandAndModel(VehiclesBrandsAndModelsData data) {
        Long brandId;
        if (data.getBrandName().equals(iVehicleBrand.find(new VehicleBrandFilter(data.getBrandName())).get(0).getName())) {
            brandId = iVehicleBrand.find(new VehicleBrandFilter(data.getBrandName())).get(0).getId();
        } else {
            brandId = iVehicleBrand.add(new VehicleBrandData(data.getBrandName()));
        }
        if (!iVehicleModel.find(new VehicleModelFilter(data.getModelName())).contains(data.getModelName())) {
            iVehicleModel.add(new VehicleModelData(null, brandId, data.getModelName()));
        } else {
            throw new IllegalArgumentException("Model" + data.getModelName() + " already exists");
        }
    }

    public void updateBrandAndModel(VehiclesBrandsAndModelsData data) {
        brandJdbcRepository.update(new VehicleBrandData(data.getBrandId(), data.getBrandName(), data.getModifyDate()));
        modelJdbcRepository.update(new VehicleModelData(data.getModelId(), data.getModelName(), data.getModifyDate()));
    }


    private List<VehiclesBrandsAndModelsData> getBrandsAndModels(List<Map<String, Object>> data) {
        List<VehiclesBrandsAndModelsData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new VehiclesBrandsAndModelsData(
                    getLong(row, "brandId"),
                    getLong(row, "modelId"),
                    getString(row, "brandName"),
                    getString(row, "modelName")
            ));
        }
        return list;
    }

}
