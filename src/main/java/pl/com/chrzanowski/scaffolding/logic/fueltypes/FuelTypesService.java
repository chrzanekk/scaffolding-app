package pl.com.chrzanowski.scaffolding.logic.fueltypes;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.IFuelTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getString;

@Service
public class FuelTypesService implements IFuelTypes {

    private FuelTypeJdbcRepository fuelTypeJdbcRepository;

    public FuelTypesService(FuelTypeJdbcRepository fuelTypeJdbcRepository) {
        this.fuelTypeJdbcRepository = fuelTypeJdbcRepository;
    }

    public List<FuelTypeData> find(FuelTypeFilter filter) {
        return getFuelTypes(fuelTypeJdbcRepository.find(filter));
    }

    private List<FuelTypeData> getFuelTypes(List<Map<String, Object>> data) {
        List<FuelTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new FuelTypeData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }


}
