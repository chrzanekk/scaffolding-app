package pl.com.chrzanowski.scaffolding.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static pl.com.chrzanowski.scaffolding.logic.DictionaryType.*;

@Service
@Transactional
public class DictionariesService {
    private static final Logger log = LoggerFactory.getLogger(DictionariesService.class);

    private DictionariesJdbcRepository dictionariesJdbcRepository;
    private IFuelType iFuelType;
    private IVehicleType iVehicleType;
    private IServiceActonType iServiceActonType;
    private ITireSeason iTireSeason;
    private IVehicleBrand iVehicleBrand;
    private IVehicleModel iVehicleModel;

    public DictionariesService(DictionariesJdbcRepository dictionariesJdbcRepository,
                               IVehicleType iVehicleType,
                               IFuelType iFuelType,
                               IServiceActonType iServiceActonType,
                               ITireSeason iTireSeason,
                               IVehicleBrand iVehicleBrand,
                               IVehicleModel iVehicleModel) {
        this.dictionariesJdbcRepository = dictionariesJdbcRepository;
        this.iVehicleType = iVehicleType;
        this.iFuelType = iFuelType;
        this.iServiceActonType = iServiceActonType;
        this.iTireSeason = iTireSeason;
        this.iVehicleBrand = iVehicleBrand;
        this.iVehicleModel = iVehicleModel;
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
        } else if (VEHICLE_TIRES_STATUS == type) {
            return getTireStatus(lang);
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
        } else if (Language.PL == lang) {
            list.add(new DictionaryData("pl", "Polski", lang.getCode()));
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
        List<VehicleTypeData> vehicleTypes = iVehicleType.find(new VehicleTypeFilter());

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
        List<FuelTypeData> fuelTypes = iFuelType.find(new FuelTypeFilter());

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
        List<ServiceActionTypeData> actionTypes = iServiceActonType.find(new ServiceActionTypesFilter());

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
        List<TireSeasonData> tireSeasons = iTireSeason.find(new TireSeasonFilter());

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
        List<VehicleBrandData> brands = iVehicleBrand.find(new VehicleBrandFilter());

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
        List<VehicleModelData> models = iVehicleModel.find(new VehicleModelFilter());

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

        if(Language.PL == lang) {
            list.add(new DictionaryData(VehicleTiresStatus.MOUNTED.getCode(),"W użytku", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.STOKED.getCode(),"W magazynie", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.DISPOSED.getCode(),"Zutylizowane", lang.getCode()));
        } else if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(VehicleTiresStatus.MOUNTED.getCode(),"Mounted", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.STOKED.getCode(),"Stoked", lang.getCode()));
            list.add(new DictionaryData(VehicleTiresStatus.DISPOSED.getCode(),"Disposed", lang.getCode()));
        }
        return list;
    }

}
