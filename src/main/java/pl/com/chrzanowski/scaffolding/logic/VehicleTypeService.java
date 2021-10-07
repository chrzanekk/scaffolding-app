package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTypeFilter;

import java.util.List;

@Service
public class VehicleTypeService {

    private VehicleTypeJdbcRepository vehicleTypeJdbcRepository;

    public VehicleTypeService(VehicleTypeJdbcRepository vehicleTypeJdbcRepository) {
        this.vehicleTypeJdbcRepository = vehicleTypeJdbcRepository;
    }

    public List<VehicleTypeData> find(VehicleTypeFilter filter) {
        return vehicleTypeJdbcRepository.find(filter);
    }


}
