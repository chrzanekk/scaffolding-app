package pl.com.chrzanowski.scaffolding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class ApplicationLocaleResolver extends SessionLocaleResolver {

    @Autowired
    private UserService userService;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        setLangPreferredByCustomer(request, userService.getLoggedUser());
        return LocaleContextHolder.getLocale();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        super.setLocale(request, response, locale);
    }

    private void setLangPreferredByCustomer(HttpServletRequest request, UserData user) {
        if (user != null) {
            setLocale(request, null, Locale.forLanguageTag(user.getLanguage()));
        }
    }
}
