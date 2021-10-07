package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeFilter;

import java.util.List;

@Service
public class ScaffFuelTypeService implements IFuelType{

    private ScaffFuelTypeJdbcRepository fuelTypeJdbcRepository;

    public ScaffFuelTypeService(ScaffFuelTypeJdbcRepository fuelTypeJdbcRepository) {
        this.fuelTypeJdbcRepository = fuelTypeJdbcRepository;
    }

    public List<ScaffFuelTypeData> find(ScaffFuelTypeFilter filter) {
        return fuelTypeJdbcRepository.find(filter);
    }


}
