package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleModelFilter;

import java.util.List;

@Service
public class ScaffVehicleModelService {

    private ScaffVehiclesModelJdbcRepository scaffVehiclesModelJdbcRepository;

    public ScaffVehicleModelService(ScaffVehiclesModelJdbcRepository scaffVehiclesModelJdbcRepository) {
        this.scaffVehiclesModelJdbcRepository = scaffVehiclesModelJdbcRepository;
    }

    public List<ScaffVehicleModelData> find(ScaffVehicleModelFilter filter) {
        return scaffVehiclesModelJdbcRepository.find(filter);
    }
}
