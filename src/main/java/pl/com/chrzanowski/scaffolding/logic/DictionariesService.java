package pl.com.chrzanowski.scaffolding.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.util.*;

import static pl.com.chrzanowski.scaffolding.logic.DictionaryType.*;

@Service
@Transactional
public class DictionariesService {
    private static final Logger log = LoggerFactory.getLogger(DictionariesService.class);

    private DictionariesJdbcRepository dictionariesJdbcRepository;
    private IFuelTypes fuelTypes;
    private IVehicleTypes vehicleTypes;
    private IServiceActonTypes serviceActonTypes;
    private ITireSeasons tireSeasons;
    private IVehicleBrands vehicleBrands;
    private IVehicleModels vehicleModels;

    public DictionariesService(DictionariesJdbcRepository dictionariesJdbcRepository,
                               IVehicleTypes vehicleTypes,
                               IFuelTypes fuelTypes,
                               IServiceActonTypes serviceActonTypes,
                               ITireSeasons tireSeasons,
                               IVehicleBrands vehicleBrands,
                               IVehicleModels vehicleModels) {
        this.dictionariesJdbcRepository = dictionariesJdbcRepository;
        this.vehicleTypes = vehicleTypes;
        this.fuelTypes = fuelTypes;
        this.serviceActonTypes = serviceActonTypes;
        this.tireSeasons = tireSeasons;
        this.vehicleBrands = vehicleBrands;
        this.vehicleModels = vehicleModels;
    }

    public List<DictionaryData> getDictionary(DictionaryType type) {
        return getDictionary(type, LanguagesUtil.getCurrentLanguage());
    }

    public List<DictionaryData> getDictionary(DictionaryType type, Locale locale) {
        return getDictionary(type, LanguagesUtil.prepareLanguageFrom(locale));
    }

    @Cacheable(CacheType.DICTIONARIES)
    public List<DictionaryData> getDictionary(DictionaryType type, Language lang) {
        if (YES_NO == type) {
            return getYesNo(lang);
        } else if (USER_AUTHORITIES == type) {
            return getUserAuthorities(lang);
        } else if (LANGUAGES == type) {
            return getLanguages(lang);
        } else if (EMAIL_TITLES == type) {
            return getEmailTitles(lang);
        } else if (VEHICLE_TYPES == type) {
            return getVehicleTypes(lang);
        } else if (FUEL_TYPES == type) {
            return getFuelTypes(lang);
        } else if (SERVICE_ACTION_TYPES == type) {
            return getActionTypes(lang);
        } else if (TIRE_SEASONS == type) {
            return getTireSeasons(lang);
        } else if (VEHICLE_BRANDS == type) {
            return getBrands(lang);
        } else if (VEHICLE_MODELS == type) {
            return getModels(lang);
        } else if (TIRE_STATUS == type) {
            return getTireStatus(lang);
        } else if (TIRE_OLD_STATUS == type) {
            return getOldTireStatus(lang);
        } else if (TIRE_SPEED_INDEXES == type) {
            return getTireSpeedIndexes(lang);
        } else if (TIRE_CAPACITY_INDEXES == type) {
            return getTireCapacityIndexes(lang);
        } else if (TIRE_REINFORCED == type) {
            return getReinforced(lang);
        } else if (TIRE_TYPE == type) {
            return getTireType(lang);
        } else if (TAX_RATE == type) {
            return getTaxRate(lang);
        }
        throw new IllegalArgumentException("Dictionary no defined: " + type + " for language: " + lang);
    }


    private List<DictionaryData> getYesNo(Language lang) {
        return Language.PL == lang ?
                Arrays.asList(new DictionaryData("true", "Tak", lang.getCode()), new DictionaryData("false", "Nie", lang.getCode())) :
                Arrays.asList(new DictionaryData("true", "Yes", lang.getCode()), new DictionaryData("false", "No", lang.getCode()));
    }

    private List<DictionaryData> getUserAuthorities(Language lang) {

        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(UserAuthority.ADMIN.getCode(), "Admin", lang.getCode()));
            list.add(new DictionaryData(UserAuthority.USER.getCode(), "User", lang.getCode()));
        } else if (Language.PL == lang) {
            list.add(new DictionaryData(UserAuthority.ADMIN.getCode(), "Admin", lang.getCode()));
            list.add(new DictionaryData(UserAuthority.USER.getCode(), "Użytkownik", lang.getCode()));
        }

        return list;
    }

    private List<DictionaryData> getLanguages(Language lang) {
        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData("pl", "Polish", lang.getCode()));
            list.add(new DictionaryData("en", "English", lang.getCode()));
        } else if (Language.PL == lang) {
            list.add(new DictionaryData("pl", "Polski", lang.getCode()));
            list.add(new DictionaryData("en", "Angielski", lang.getCode()));
        }

        return list;
    }

    private List<DictionaryData> getEmailTitles(Language lang) {
        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(EmailTitle.AFTER_REGISTRATION.getCode(), "Registration confirmation", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_PASSWORD_CHANGE.getCode(), "Password has been changed", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.PASSWORD_RESET.getCode(), "Password reset", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.NEWSLETTER.getCode(), "Newsletter", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.EMAIL_CONFIRMATION_LINK.getCode(), "Confirm your email", lang.getCode()));

        } else if (Language.PL == lang) {
            list.add(new DictionaryData(EmailTitle.AFTER_REGISTRATION.getCode(), "Potwierdzenie rejestracji", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_PASSWORD_CHANGE.getCode(), "Hasło zostało zmienione", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.PASSWORD_RESET.getCode(), "Resetowanie hasła", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.NEWSLETTER.getCode(), "Nowości (Newsletter)", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.EMAIL_CONFIRMATION_LINK.getCode(), "Potwierdź swój email", lang.getCode()));
        }

        return list;
    }

    private List<DictionaryData> getVehicleTypes(Language lang) {
        List<VehicleTypeData> vehicleTypes = this.vehicleTypes.find(new VehicleTypeFilter());

        List<DictionaryData> dictionaryDataList = new ArrayList<>();

        for (VehicleTypeData vehicleType : vehicleTypes) {
            dictionaryDataList.add(new DictionaryData(
                    vehicleType.getId(),
                    vehicleType.getName(),
                    lang.getCode()));
        }
        return dictionaryDataList;
    }

    private List<DictionaryData> getFuelTypes(Language lang) {
        List<FuelTypeData> fuelTypes = this.fuelTypes.find(new FuelTypeFilter());

        List<DictionaryData> dictionaryDataList = new ArrayList<>();

        for (FuelTypeData fuelType : fuelTypes) {
            dictionaryDataList.add(new DictionaryData(
                    fuelType.getId(),
                    fuelType.getName(),
                    lang.getCode()));
        }
        return dictionaryDataList;
    }

    private List<DictionaryData> getActionTypes(Language lang) {
        List<ServiceActionTypeData> actionTypes = serviceActonTypes.find(new ServiceActionTypesFilter());

        List<DictionaryData> dictionaryDataList = new ArrayList<>();

        for (ServiceActionTypeData data : actionTypes) {
            dictionaryDataList.add(new DictionaryData(
                    data.getId(),
                    data.getName(),
                    lang.getCode()
            ));
        }
        return dictionaryDataList;
    }

    private List<DictionaryData> getTireSeasons(Language lang) {
        List<TireSeasonData> tireSeasons = this.tireSeasons.find(new TireSeasonFilter());

        List<DictionaryData> dictionaryDataList = new ArrayList<>();

        for (TireSeasonData data : tireSeasons) {
            dictionaryDataList.add(new DictionaryData(
                    data.getId(),
                    data.getName(),
                    lang.getCode()
            ));
        }
        return dictionaryDataList;
    }

    private List<DictionaryData> getBrands(Language lang) {
        List<VehicleBrandData> brands = vehicleBrands.find(new VehicleBrandFilter());

        List<DictionaryData> dictionaryDataList = new ArrayList<>();
        for (VehicleBrandData data : brands) {
            dictionaryDataList.add(new DictionaryData(
                    data.getId(),
                    data.getName(),
                    lang.getCode()
            ));
        }
        return dictionaryDataList;
    }

    private List<DictionaryData> getModels(Language lang) {
        List<VehicleModelData> models = vehicleModels.find(new VehicleModelFilter());

        List<DictionaryData> dictionaryDataList = new ArrayList<>();
        for (VehicleModelData data : models) {
            dictionaryDataList.add(new DictionaryData(
                    data.getId(),
                    data.getName(),
                    lang.getCode(),
                    data.getBrandId()
            ));
        }
        return dictionaryDataList;
    }

    private List<DictionaryData> getTireStatus(Language lang) {

        List<DictionaryData> list = new ArrayList<>();

        if (Language.PL == lang) {
            list.add(new DictionaryData(VehicleTiresStatus.MOUNTED.getCode(), "W użytku", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.STOKED.getCode(), "W magazynie", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.DISPOSED.getCode(), "Zutylizowane", lang.getCode()));
        } else if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(VehicleTiresStatus.MOUNTED.getCode(), "Mounted", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.STOKED.getCode(), "Stoked", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.DISPOSED.getCode(), "Disposed", lang.getCode()));
        }
        return list;
    }

    private List<DictionaryData> getOldTireStatus(Language lang) {

        List<DictionaryData> list = new ArrayList<>();

        if (Language.PL == lang) {
            list.add(new DictionaryData(VehicleTiresStatus.STOKED.getCode(), "W magazynie", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.DISPOSED.getCode(), "Zutylizowane", lang.getCode()));
        } else if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(VehicleTiresStatus.STOKED.getCode(), "Stoked", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.DISPOSED.getCode(), "Disposed", lang.getCode()));
        }
        return list;
    }

    private List<DictionaryData> getTireSpeedIndexes(Language lang) {
        List<DictionaryData> speedIndexesList = new ArrayList<>();
        Map<String, Integer> speedIndexes = VehicleTireSpeed.SPEED_INDEXES;
        for (Map.Entry<String, Integer> data : speedIndexes.entrySet()) {
            speedIndexesList.add(new DictionaryData(
                    data.getKey(),
                    data.getValue().toString(),
                    lang.getCode()
            ));
        }
        return speedIndexesList;
    }

    private List<DictionaryData> getTireCapacityIndexes(Language lang) {
        List<DictionaryData> capacityIndexesList = new ArrayList<>();
        Map<Integer, Integer> capacityIndexesMap = VehicleTireLoadCapacity.LOAD_INDEXES;
        for (Map.Entry<Integer, Integer> data : capacityIndexesMap.entrySet()) {
            capacityIndexesList.add(new DictionaryData(
                    data.getKey().toString(),
                    data.getValue().toString(),
                    lang.getCode()
            ));
        }
        return capacityIndexesList;
    }

    private List<DictionaryData> getReinforced(Language lang) {
        return Language.PL == lang ?
                Arrays.asList(new DictionaryData("XL", "XL - Opona wzmacniana", lang.getCode()),
                            new DictionaryData("RF", "RF - Opona wzmacniana", lang.getCode()),
                            new DictionaryData("C", "C - Opona dostawcza", lang.getCode()),
                            new DictionaryData("SL", "SL - Opona standardowa", lang.getCode())) :
                Arrays.asList(new DictionaryData("XL", "XL - Extra Load Tire", lang.getCode()),
                            new DictionaryData("RF", "RF - Reinforced Tire", lang.getCode()),
                            new DictionaryData("C", "C - Commercial Tire", lang.getCode()),
                            new DictionaryData("SL", "SL - Standard Tire", lang.getCode()));
    }

    private List<DictionaryData> getTireType(Language lang) {
        return Language.PL == lang ?
                Arrays.asList(new DictionaryData("R", "R - Opona radialna", lang.getCode()),
                            new DictionaryData("D", "D - Opona diagonalna", lang.getCode())

                           ) :
                Arrays.asList(new DictionaryData("R", "R - Radial tire", lang.getCode()),
                            new DictionaryData("D", "D - Diagonal tire", lang.getCode()));
    }

    private List<DictionaryData> getTaxRate(Language lang) {
        return Language.PL == lang ?
                Arrays.asList(new DictionaryData("0.00", "VAT 0%", lang.getCode()),
                            new DictionaryData("0.08", "VAT 8%", lang.getCode()),
                            new DictionaryData("0.23", "VAT 23%", lang.getCode())
                           ) :
                Arrays.asList(new DictionaryData("0.00", "Value Added Tax - 0%", lang.getCode()),
                        new DictionaryData("0.08", "Value Added Tax 8%", lang.getCode()),
                        new DictionaryData("0.23", "Value Added Tax 23%", lang.getCode()));
    }

}
