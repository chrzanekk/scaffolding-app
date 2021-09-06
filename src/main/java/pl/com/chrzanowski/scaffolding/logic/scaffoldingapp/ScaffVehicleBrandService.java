package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleBrandFilter;

import java.util.List;

@Service
public class ScaffVehicleBrandService {

    private ScaffVehicleBrandJdbcRepository scaffVehicleBrandJdbcRepository;

    public ScaffVehicleBrandService(ScaffVehicleBrandJdbcRepository scaffVehicleBrandJdbcRepository) {
        this.scaffVehicleBrandJdbcRepository = scaffVehicleBrandJdbcRepository;
    }

    public List<ScaffVehicleBrandData>find(ScaffVehicleBrandFilter filter) {
        return scaffVehicleBrandJdbcRepository.find(filter);
    }
}
