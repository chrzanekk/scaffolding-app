package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;

import java.sql.SQLException;
import java.util.List;

@Service
public class VehiclesService {

    private VehiclesJdbcRepository vehiclesJdbcRepository;

    public VehiclesService(VehiclesJdbcRepository vehiclesJdbcRepository) {
        this.vehiclesJdbcRepository = vehiclesJdbcRepository;

    }

    public List<VehicleData> find(VehicleFilter filter) throws SQLException {
        return vehiclesJdbcRepository.find(filter);
    }

    public VehicleData findById(VehicleFilter filter) throws SQLException {
        return vehiclesJdbcRepository.get(filter);
    }


    public Long add(VehicleData data) {
        return vehiclesJdbcRepository.create(data);
    }

    public void update(VehicleData data) throws SQLException {
        vehiclesJdbcRepository.update(data);
    }

}
