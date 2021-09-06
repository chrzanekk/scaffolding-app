package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehiclesFilter;

import java.sql.SQLException;
import java.util.List;

@Service
public class ScaffVehiclesService {

    private ScaffVehiclesJdbcRepository scaffVehiclesJdbcRepository;

    public ScaffVehiclesService(ScaffVehiclesJdbcRepository scaffVehiclesJdbcRepository) {
        this.scaffVehiclesJdbcRepository = scaffVehiclesJdbcRepository;
    }

    public List<ScaffVehicleData> find(ScaffVehiclesFilter filter) throws SQLException {
        return scaffVehiclesJdbcRepository.find(filter);
    }
}
