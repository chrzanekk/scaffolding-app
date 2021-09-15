package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleTypeFilter;

import java.util.List;

@Service
public class ScaffVehicleTypeService {

    private ScaffVehicleTypeJdbcRepository vehicleTypeJdbcRepository;

    public ScaffVehicleTypeService(ScaffVehicleTypeJdbcRepository vehicleTypeJdbcRepository) {
        this.vehicleTypeJdbcRepository = vehicleTypeJdbcRepository;
    }

    public List<ScaffVehicleTypeData> find(ScaffVehicleTypeFilter filter) {
        return vehicleTypeJdbcRepository.find(filter);
    }


}
