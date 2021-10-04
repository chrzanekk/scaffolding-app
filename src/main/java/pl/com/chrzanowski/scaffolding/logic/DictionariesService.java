package pl.com.chrzanowski.scaffolding.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.chrzanowski.scaffolding.domain.DictionaryData;
import cleaning.toDelete.logic.courseplatform.*;
import java.util.*;


import static pl.com.chrzanowski.scaffolding.logic.DictionaryType.*;

@Service
@Transactional
public class DictionariesService {
    private static final Logger log = LoggerFactory.getLogger(DictionariesService.class);

    private DictionariesJdbcRepository dictionariesJdbcRepository;


    public DictionariesService(DictionariesJdbcRepository dictionariesJdbcRepository) {
        this.dictionariesJdbcRepository = dictionariesJdbcRepository;
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
        } else if (CUSTOMER_AUTHORITIES == type) {
            return getCustomerAuthorities(lang);
        } else if(LANGUAGES == type) {
            return getLanguages(lang);
        } else if(EMAIL_TITLES == type) {
            return getEmailTitles(lang);
        }
        throw new IllegalArgumentException("Dictionary no defined: " + type + " for language: " + lang);
    }


    private List<DictionaryData> getYesNo(Language lang) {
        return Language.PL == lang ?
                Arrays.asList(new DictionaryData("true", "Tak", lang.getCode()), new DictionaryData("false", "Nie", lang.getCode())) :
                Arrays.asList(new DictionaryData("true", "Yes", lang.getCode()), new DictionaryData("false", "No", lang.getCode()));
    }

    private List<DictionaryData> getCustomerAuthorities(Language lang) {

        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(CustomerAuthority.ADMIN.getCode(), "Admin", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.USER.getCode(), "User", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.EMPLOYER.getCode(), "Employer", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.TEACHER.getCode(), "Teacher", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.STUDENT.getCode(), "Student", lang.getCode()));
        } else if (Language.PL == lang) {
            list.add(new DictionaryData(CustomerAuthority.ADMIN.getCode(), "Admin", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.USER.getCode(), "Użytkownik", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.EMPLOYER.getCode(), "Pracodawca", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.TEACHER.getCode(), "Nauczyciel", lang.getCode()));
            list.add(new DictionaryData(CustomerAuthority.STUDENT.getCode(), "Kursant", lang.getCode()));
        }

        return list;
    }


    private List<DictionaryData> getLanguages(Language lang) {
        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
//            list.add(new DictionaryData("en", "English", lang.getCode()));
            list.add(new DictionaryData("pl", "Polish", lang.getCode()));
        } else if (Language.PL == lang) {
//            list.add(new DictionaryData("en", "Angielski", lang.getCode()));
            list.add(new DictionaryData("pl", "Polski", lang.getCode()));
        }

        return list;
    }

    private List<DictionaryData> getEmailTitles(Language lang) {
        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(EmailTitle.AFTER_REGISTRATION.getCode(), "Registration confirmation", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_ORDER.getCode(), "Order confirmation", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_PASSWORD_CHANGE.getCode(), "Password has been changed", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.PASSWORD_RESET.getCode(), "Password reset", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.NEWSLETTER.getCode(), "Newsletter", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.EMAIL_CONFIRMATION_LINK.getCode(), "Confirm your email", lang.getCode()));

        } else if (Language.PL == lang) {
            list.add(new DictionaryData(EmailTitle.AFTER_REGISTRATION.getCode(), "Potwierdzenie rejestracji", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_ORDER.getCode(), "Potwierdzenie złożenia zamówienia", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_PASSWORD_CHANGE.getCode(), "Hasło zostało zmienione", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.PASSWORD_RESET.getCode(), "Resetowanie hasła", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.NEWSLETTER.getCode(), "Nowości (Newsletter)", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.EMAIL_CONFIRMATION_LINK.getCode(), "Potwierdź swój email", lang.getCode()));
        }

        return list;
    }


}
