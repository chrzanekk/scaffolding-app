package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.DictionaryData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class VehicleTiresService implements IVehicleTires {

    private VehicleTiresJdbcRepository tiresJdbcRepository;
    private DictionariesService dictionariesService;

    public VehicleTiresService(VehicleTiresJdbcRepository tiresJdbcRepository,
                               DictionariesService dictionariesService) {
        this.tiresJdbcRepository = tiresJdbcRepository;
        this.dictionariesService = dictionariesService;
    }

    @Override
    public List<VehicleTiresData> find(VehicleTiresFilter filter) {
        return getTires(tiresJdbcRepository.find(filter));
    }

    @Override
    public VehicleTiresData findById(VehicleTiresFilter filter) {
        return getTires(tiresJdbcRepository.find(filter)).get(0);
    }

    private List<VehicleTiresData> getTires(List<Map<String, Object>> data) {
        List<VehicleTiresData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new VehicleTiresData(
                    getLong(row, "id"),
                    getLong(row, "vehicleId"),
                    getLong(row, "tireId"),
                    convertTireStatus(getString(row, "status")),
                    getInteger(row, "productionYear"),
                    getDate(row, "purchaseDate"),
                    getString(row, "brand"),
                    getString(row, "model"),
                    getInteger(row, "width"),
                    getInteger(row, "profile"),
                    getInteger(row, "diameter"),
                    getString(row, "speedIndex"),
                    getInteger(row, "capacityIndex"),
                    getString(row, "reinforced"),
                    getLong(row, "seasonId"),
                    getString(row, "seasonName"),
                    convertRunOnFlat(getBoolean(row, "runOnFlat"))
                    ));
        }
        return list;
    }

    //todo    do sformatowania: reinforced,
    private String convertRunOnFlat(Boolean condition) {
        Language lang = LanguagesUtil.getCurrentLanguage();
        List<DictionaryData> yesNo = dictionariesService.getDictionary(DictionaryType.YES_NO,lang);
        String result = "";
        if (condition.equals(true)) {
            result = yesNo.get(0).getValue();
        } else {
            result = yesNo.get(1).getValue();
        }
        return result;
    }

    private String convertTireStatus(String status) {
        String result = "";
        List<DictionaryData> tireStatus = dictionariesService.getDictionary(DictionaryType.TIRE_STATUS,LanguagesUtil.getCurrentLanguage());
        for(DictionaryData data : tireStatus) {
            if(data.getCode().equals(status)) {
                result = data.getValue();
            }
        }
        return result;
    }

}