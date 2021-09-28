package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleFilter;

import java.sql.SQLException;
import java.util.List;

@Service
public class ScaffVehiclesService {

    private ScaffVehiclesJdbcRepository scaffVehiclesJdbcRepository;

    public ScaffVehiclesService(ScaffVehiclesJdbcRepository scaffVehiclesJdbcRepository) {
        this.scaffVehiclesJdbcRepository = scaffVehiclesJdbcRepository;

    }

    public List<ScaffVehicleData> find(ScaffVehicleFilter filter) throws SQLException {
        return scaffVehiclesJdbcRepository.find(filter);
    }

    public ScaffVehicleData findById(ScaffVehicleFilter filter) throws SQLException {
        return scaffVehiclesJdbcRepository.get(filter);
    }


    public Long add(ScaffVehicleData data) {
        return scaffVehiclesJdbcRepository.create(data);
    }

    public void update(ScaffVehicleData data) throws SQLException {
        scaffVehiclesJdbcRepository.update(data);
    }

}
