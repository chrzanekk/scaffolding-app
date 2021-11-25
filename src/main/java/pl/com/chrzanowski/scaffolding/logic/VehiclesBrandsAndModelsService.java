//package pl.com.chrzanowski.scaffolding.logic;
//
//import org.springframework.stereotype.Service;
//import pl.com.chrzanowski.scaffolding.domain.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
//import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;
//
//@Service
//public class VehiclesBrandsAndModelsService {
//    private VehiclesBrandsAndModelsJdbcRepository vehiclesBrandsAndModelsJdbcRepository;
//    private VehicleBrandJdbcRepository brandJdbcRepository;
//    private VehicleModelJdbcRepository modelJdbcRepository;
//    private IVehicleBrands iVehicleBrands;
//    private IVehicleModels iVehicleModels;
//
//    public VehiclesBrandsAndModelsService(VehiclesBrandsAndModelsJdbcRepository vehiclesBrandsAndModelsJdbcRepository,
//                                          VehicleBrandJdbcRepository brandJdbcRepository,
//                                          VehicleModelJdbcRepository modelJdbcRepository,
//                                          IVehicleBrands iVehicleBrands,
//                                          IVehicleModels iVehicleModels) {
//
//        this.vehiclesBrandsAndModelsJdbcRepository = vehiclesBrandsAndModelsJdbcRepository;
//        this.brandJdbcRepository = brandJdbcRepository;
//        this.modelJdbcRepository = modelJdbcRepository;
//        this.iVehicleBrands = iVehicleBrands;
//        this.iVehicleModels = iVehicleModels;
//    }
//
//
//    public List<VehiclesBrandsAndModelsData> find(VehiclesBrandsAndModelsFilter filter) {
//        return getBrandsAndModels(vehiclesBrandsAndModelsJdbcRepository.find(filter));
//    }
//
//    public void addBrandAndModel(VehiclesBrandsAndModelsData data) {
//        Long brandId;
//        if (data.getBrandName().equals(iVehicleBrands.find(new VehicleBrandFilter(data.getBrandName())).get(0).getName())) {
//            brandId = iVehicleBrands.find(new VehicleBrandFilter(data.getBrandName())).get(0).getId();
//        } else {
//            brandId = iVehicleBrands.add(new VehicleBrandData(data.getBrandName()));
//        }
//        if (iVehicleModels.find(new VehicleModelFilter(data.getModelName())).contains(null)) {
//            iVehicleModels.add(new VehicleModelData(null, brandId, data.getModelName()));
//        } else {
//            throw new IllegalArgumentException("Model" + data.getModelName() + " already exists");
//        }
//    }
//
//    public void updateBrandAndModel(VehiclesBrandsAndModelsData data) {
//        brandJdbcRepository.update(new VehicleBrandData(data.getBrandId(), data.getBrandName(), data.getModifyDate()));
//        modelJdbcRepository.update(new VehicleModelData(data.getModelId(), data.getModelName(), data.getModifyDate()));
//    }
//
//
//    private List<VehiclesBrandsAndModelsData> getBrandsAndModels(List<Map<String, Object>> data) {
//        List<VehiclesBrandsAndModelsData> list = new ArrayList<>();
//        for (Map<String, Object> row : data) {
//            list.add(new VehiclesBrandsAndModelsData(
//                    getLong(row, "brandId"),
//                    getLong(row, "modelId"),
//                    getString(row, "brandName"),
//                    getString(row, "modelName")
//            ));
//        }
//        return list;
//    }
//
//}
