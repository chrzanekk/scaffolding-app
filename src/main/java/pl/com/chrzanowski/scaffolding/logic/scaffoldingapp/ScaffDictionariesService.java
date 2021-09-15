package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffDictionaryData;
import pl.com.chrzanowski.scaffolding.logic.CacheType;
import pl.com.chrzanowski.scaffolding.logic.courseplatform.LanguagesUtil;
import pl.com.chrzanowski.scaffolding.logic.Language;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class ScaffDictionariesService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ScaffDictionariesService.class);

    private ScaffDictionariesJdbcRepository scaffDictionariesJdbcRepository;
    private ScaffUserJdbcRepository scaffUserJdbcRepository;
    private ScaffFuelTypeJdbcRepository fuelTypeJdbcRepository;

    public ScaffDictionariesService(ScaffDictionariesJdbcRepository scaffDictionariesJdbcRepository, ScaffUserJdbcRepository scaffUserJdbcRepository, ScaffFuelTypeJdbcRepository fuelTypeJdbcRepository) {
        this.scaffDictionariesJdbcRepository = scaffDictionariesJdbcRepository;
        this.scaffUserJdbcRepository = scaffUserJdbcRepository;
        this.fuelTypeJdbcRepository = fuelTypeJdbcRepository;
    }

    public List<ScaffDictionaryData> getDictionary(ScaffDictionaryType type) {
        return getDictionary(type, LanguagesUtil.getCurrentLanguage());
    }

    public List<ScaffDictionaryData> getDictionary(ScaffDictionaryType type, Locale locale) {
        return getDictionary(type, LanguagesUtil.prepareLanguageFrom(locale));
    }

    @Cacheable(CacheType.DICTIONARIES)
    public List<ScaffDictionaryData> getDictionary(ScaffDictionaryType type, Language lang) {
        if(ScaffDictionaryType.FUEL_TYPES == type) {
            return getFuelTypes(lang);
        }
        else if (ScaffDictionaryType.VEHICLE_TYPES == type){
            return null;
        }
        throw new IllegalArgumentException("Dictionary no defined: " + type + " for language: " + lang);
    }


    private List<ScaffDictionaryData> getFuelTypes(Language lang) {

        List<ScaffDictionaryData> list = new ArrayList<>();
        if(Language.PL == lang) {
            list.add(new ScaffDictionaryData(ScaffFuelType.BENZINE_PL.getType(),lang.getCode()));
            list.add(new ScaffDictionaryData(ScaffFuelType.DIESEL_PL.getType(),lang.getCode()));
        } else if(Language.US == lang|| Language.EN == lang) {
            list.add(new ScaffDictionaryData(ScaffFuelType.BENZINE_EN.getType(),lang.getCode()));
            list.add(new ScaffDictionaryData(ScaffFuelType.DIESEL_EN.getType(),lang.getCode()));
        }
        return list;
    }

}
