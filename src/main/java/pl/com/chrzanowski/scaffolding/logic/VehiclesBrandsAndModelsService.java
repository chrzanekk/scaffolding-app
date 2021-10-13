package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class VehiclesBrandsAndModelsService {
    private VehiclesBrandsAndModelsJdbcRepository vehiclesBrandsAndModelsJdbcRepository;

    public VehiclesBrandsAndModelsService(VehiclesBrandsAndModelsJdbcRepository vehiclesBrandsAndModelsJdbcRepository) {

        this.vehiclesBrandsAndModelsJdbcRepository = vehiclesBrandsAndModelsJdbcRepository;
    }


    public List<VehiclesBrandsAndModelsData> find(VehiclesBrandsAndModelsFilter filter) {
        return getBrandsAndModels(vehiclesBrandsAndModelsJdbcRepository.find(filter));
    }


    private List<VehiclesBrandsAndModelsData> getBrandsAndModels(List<Map<String,Object>> data) {
        List<VehiclesBrandsAndModelsData> list = new ArrayList<>();
        for (Map<String,Object> row : data) {
            list.add(new VehiclesBrandsAndModelsData(
                    getLong(row, "brandId"),
                    getLong(row, "modelId"),
                    getString(row,"brandName"),
                    getString(row, "modelName")
            ));
        }
        return list;
    }
}
