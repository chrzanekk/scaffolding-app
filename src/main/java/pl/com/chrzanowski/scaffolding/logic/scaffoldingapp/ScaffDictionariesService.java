package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffDictionaryData;
import pl.com.chrzanowski.scaffolding.logic.CacheType;
import cleaning.toDelete.logic.courseplatform.LanguagesUtil;
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

        return list;
    }

}
