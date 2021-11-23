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
    private DataValidateService dataValidateService;

    public VehicleTiresService(VehicleTiresJdbcRepository tiresJdbcRepository,
                               DictionariesService dictionariesService,
                               DataValidateService dataValidateService) {
        this.tiresJdbcRepository = tiresJdbcRepository;
        this.dictionariesService = dictionariesService;
        this.dataValidateService = dataValidateService;
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
        tiresJdbcRepository.updateTire(data);
        tiresJdbcRepository.update(data);
    }

    @Override
    public void updateTire(VehicleTiresData data) {
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
            tiresJdbcRepository.update(new VehicleTiresData(existingMountedTire, "s"));
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
        VehicleTiresData existingTire = getTire(filter);
        return existingTire.getStatus().equals(convertTireStatus("m"));
    }

    private void validateData(VehicleTiresData data) {
        dataValidateService.validateTextField(data.getBrand(), "Marka");
        dataValidateService.validateTextField(data.getModel(), "Model");
        dataValidateService.validateValue(data.getProductionYear(), "Rok produkcji");
        dataValidateService.validateDate(data.getPurchaseDate(), "Data zakupu");
        dataValidateService.validateTextField(data.getStatus(), "Status opon");
        dataValidateService.validateValue(data.getSeasonId(), "Sezon");
        dataValidateService.validateValue(data.getWidth(), "Szerokość");
        dataValidateService.validateValue(data.getProfile(), "Profil");
        dataValidateService.validateTextField(data.getType(), "Typ");
        dataValidateService.validateValue(data.getDiameter(), "Średnica");
        dataValidateService.validateTextField(data.getSpeedIndex(), "Index prędkości");
        dataValidateService.validateValue(data.getCapacityIndex(), "Index nośności");
        dataValidateService.validateTextField(data.getReinforced(), "Czy jest wzmacniana(Reinforced)?");
        dataValidateService.validateTextField(data.getRunOnFlat(), "Run on flat?");
    }

}
