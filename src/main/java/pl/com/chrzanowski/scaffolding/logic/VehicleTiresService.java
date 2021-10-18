package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class VehicleTiresService implements IVehicleTires {

    private VehicleTiresJdbcRepository tiresJdbcRepository;

    public VehicleTiresService(VehicleTiresJdbcRepository tiresJdbcRepository) {
        this.tiresJdbcRepository = tiresJdbcRepository;
    }

    @Override
    public List<VehicleTiresData> find(VehicleTiresFilter filter) {
        return null;
    }

    private List<VehicleTiresData> getTires(List<Map<String, Object>> data) {
        List<VehicleTiresData> list = new ArrayList<>();
        for (Map<String,Object> row : data) {
            list.add(new VehicleTiresData(
                    getLong(row,"id"),
                    getLong(row, "vehicleId"),
                    getLong(row, "tireId"),
                    getString(row, "status"),
                    getInteger(row,"productionYear"),
                    getDate(row,"purchaseDate"),
                    getString(row, "brand"),
                    getString(row, "model"),
                    getInteger(row,"width"),
                    getInteger(row,"profile"),
                    getInteger(row,"diameter"),
                    getString(row, "speedIndex"),
                    getInteger(row, "capacityIndex"),
                    getString(row, "reinforced"),
                    getBoolean(row, "runOnFlat"),
                    getLong(row,"seasonId"),
                    getString(row, "seasonName")
            ));
        }
        return list;
    }
}
