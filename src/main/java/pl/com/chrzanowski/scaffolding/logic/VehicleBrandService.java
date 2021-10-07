package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;

import java.util.List;

@Service
public class VehicleBrandService {

    private VehicleBrandJdbcRepository vehicleBrandJdbcRepository;

    public VehicleBrandService(VehicleBrandJdbcRepository vehicleBrandJdbcRepository) {
        this.vehicleBrandJdbcRepository = vehicleBrandJdbcRepository;
    }

    public List<VehicleBrandData>find(VehicleBrandFilter filter) {
        return vehicleBrandJdbcRepository.find(filter);
    }
}
