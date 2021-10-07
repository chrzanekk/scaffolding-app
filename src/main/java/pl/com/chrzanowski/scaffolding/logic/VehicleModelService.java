package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;

import java.util.List;

@Service
public class VehicleModelService {

    private VehiclesModelJdbcRepository vehiclesModelJdbcRepository;

    public VehicleModelService(VehiclesModelJdbcRepository vehiclesModelJdbcRepository) {
        this.vehiclesModelJdbcRepository = vehiclesModelJdbcRepository;
    }

    public List<VehicleModelData> find(VehicleModelFilter filter) {
        return vehiclesModelJdbcRepository.find(filter);
    }
}
