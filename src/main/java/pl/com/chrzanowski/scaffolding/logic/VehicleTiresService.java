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
    public VehicleTiresData getTire(VehicleTiresFilter filter) {
        return getTires(tiresJdbcRepository.find(filter)).get(0);
    }


    @Override
    public void create(VehicleTiresData data) {
        validateData(data);
        checkMountStatusAndUpdateToStocked(data);
        tiresJdbcRepository.create(data);
    }

    @Override
    public void update(VehicleTiresData data) {
        validateData(data);
        checkMountStatusAndUpdateToStocked(data);
        tiresJdbcRepository.updateTireProperties(data);
        tiresJdbcRepository.updateTire(data);
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
                    getString(row, "type"),
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

    private void checkMountStatusAndUpdateToStocked(VehicleTiresData data) {
        if (checkIsTiresMounted(new VehicleTiresFilter(null, data.getVehicleId(), "m"))) {
            VehicleTiresData existingMountedTire = getTire(new VehicleTiresFilter(null, data.getVehicleId(), "m"));
            tiresJdbcRepository.updateTire(new VehicleTiresData(existingMountedTire, "s"));
        }
    }

    private String convertRunOnFlat(Boolean condition) {
        Language lang = LanguagesUtil.getCurrentLanguage();
        List<DictionaryData> yesNo = dictionariesService.getDictionary(DictionaryType.YES_NO, lang);
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
        List<DictionaryData> tireStatus = dictionariesService.getDictionary(DictionaryType.TIRE_STATUS, LanguagesUtil.getCurrentLanguage());
        for (DictionaryData data : tireStatus) {
            if (data.getCode().equals(status)) {
                result = data.getValue();
            }
        }
        return result;
    }

    private boolean checkIsTiresMounted(VehicleTiresFilter filter) {
        List<VehicleTiresData> existingTires = find(filter);
        if (existingTires.isEmpty()) {
            return false;
        }
        return true;
    }

    private void validateData(VehicleTiresData data) {
        DataValidationUtil.validateTextField(data.getBrand(), "Marka");
        DataValidationUtil.validateTextField(data.getModel(), "Model");
        DataValidationUtil.validateTireProductionYear(data.getProductionYear(), "Rok produkcji");
        DataValidationUtil.validateDate(data.getPurchaseDate(), "Data zakupu");
        DataValidationUtil.validateTextField(data.getStatus(), "Status opon");
        DataValidationUtil.validateValue(data.getSeasonId(), "Sezon");
        DataValidationUtil.validateTireWidth(data.getWidth(), "Szerokość");
        DataValidationUtil.validateTireProfile(data.getProfile(), "Profil");
        DataValidationUtil.validateTextField(data.getType(), "Typ");
        DataValidationUtil.validateTireDiameter(data.getDiameter(), "Średnica");
        DataValidationUtil.validateTextField(data.getSpeedIndex(), "Index prędkości");
        DataValidationUtil.validateValue(data.getCapacityIndex(), "Index nośności");
        DataValidationUtil.validateTextField(data.getReinforced(), "Czy jest wzmacniana(Reinforced)?");
        DataValidationUtil.validateBoolean(data.isRunOnFlat(), "Run on flat?");
    }

}
