package pl.com.chrzanowski.scaffolding.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.logic.ApplicationConfigService;
import pl.com.chrzanowski.scaffolding.logic.Language;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.LanguagesUtil;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUserAuthoritiesService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUserAuthority;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUsersService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticatedUser {
    private ApplicationConfigService applicationConfigService;
    private ScaffUserAuthoritiesService scaffUserAuthoritiesService;
    private ScaffUsersService scaffUsersService;

    public AuthenticatedUser(ApplicationConfigService applicationConfigService
            , ScaffUsersService scaffUsersService,
                             ScaffUserAuthoritiesService scaffUserAuthoritiesService) {
        this.applicationConfigService = applicationConfigService;
        this.scaffUsersService = scaffUsersService;
        this.scaffUserAuthoritiesService = scaffUserAuthoritiesService;
    }

    public String getAppVersion() {
        return applicationConfigService.getVersion();
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    public List<Menu> getMenu() {

        ScaffUserData loggedUser = scaffUsersService.getLoggedUser();
        Language currentLang = LanguagesUtil.getCurrentLanguage();

        if (scaffUserAuthoritiesService.hasUserAuthority(loggedUser, ScaffUserAuthority.ADMIN)) {
            return new ArrayList<>(Arrays.asList(
                    new Menu("Flota", "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON},
                            new ArrayList<>(Arrays.asList(new Menu(chooseMenuName("Cars", "Samochody", currentLang),
                                    "/admin/vehicles", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                            ))),
                    new Menu(chooseMenuName("Data settings", "Ustawienia danych", currentLang), "#",
                            new Permissions[]{Permissions.LIFE_ADVISER_COMMON},
                            new ArrayList<>(Arrays.asList(
                                    new Menu(chooseMenuName("Workshops", "Warsztaty", currentLang),
                                            "/admin/workshops", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                                    new Menu(chooseMenuName("Service actions types", "Typy usług serwisowych", currentLang),
                                            "/admin/service-action-types", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                            )
                            )
                    ),
                    new Menu("Marketing", "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu("Newsletter", "/admin/marketing/newsletter", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Notifications", "Powiadomienia", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Send notifications", "Wyślij powiadomienia", currentLang), "/admin/notifications/send", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    )))
            ));
        } else {
            throw new IllegalArgumentException("You don't have permissions to Menu!");
        }
    }

    private String chooseMenuName(String englishName, String polishName, Language lang) {

        if (lang == Language.PL) {
            return polishName;
        } else {
            return englishName;
        }

    }
}
