package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeFilter;

import java.util.List;

@Service
public class FuelTypeService implements IFuelType{

    private FuelTypeJdbcRepository fuelTypeJdbcRepository;

    public FuelTypeService(FuelTypeJdbcRepository fuelTypeJdbcRepository) {
        this.fuelTypeJdbcRepository = fuelTypeJdbcRepository;
    }

    public List<FuelTypeData> find(FuelTypeFilter filter) {
        return fuelTypeJdbcRepository.find(filter);
    }


}
