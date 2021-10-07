package pl.com.chrzanowski.scaffolding.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.logic.*;
import pl.com.chrzanowski.scaffolding.logic.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticatedUser {
    private ApplicationConfigService applicationConfigService;
    private UserAuthoritiesService userAuthoritiesService;
    private UserService userService;

    public AuthenticatedUser(ApplicationConfigService applicationConfigService
            , UserService userService,
                             UserAuthoritiesService userAuthoritiesService) {
        this.applicationConfigService = applicationConfigService;
        this.userService = userService;
        this.userAuthoritiesService = userAuthoritiesService;
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

        UserData loggedUser = userService.getLoggedUser();
        Language currentLang = LanguagesUtil.getCurrentLanguage();

        if (userAuthoritiesService.hasUserAuthority(loggedUser, UserAuthority.ADMIN)) {
            return new ArrayList<>(Arrays.asList(
                    new Menu("Flota", "#", new Permissions[]{Permissions.ADMIN},
                            new ArrayList<>(Arrays.asList(new Menu(chooseMenuName("Cars", "Samochody", currentLang),
                                    "/admin/vehicles", new Permissions[]{Permissions.ADMIN}, Collections.emptyList())
                            ))),
                    new Menu(chooseMenuName("Data settings", "Ustawienia danych", currentLang), "#",
                            new Permissions[]{Permissions.ADMIN},
                            new ArrayList<>(Arrays.asList(
                                    new Menu(chooseMenuName("Workshops", "Warsztaty", currentLang),
                                            "/admin/workshops", new Permissions[]{Permissions.ADMIN}, Collections.emptyList()),
                                    new Menu(chooseMenuName("Service actions types", "Typy usług serwisowych", currentLang),
                                            "/admin/service-action-types", new Permissions[]{Permissions.ADMIN}, Collections.emptyList())
                            )
                            )
                    ),
                    new Menu("Marketing", "#", new Permissions[]{Permissions.ADMIN}, new ArrayList<>(Arrays.asList(
                            new Menu("Newsletter", "/admin/marketing/newsletter", new Permissions[]{Permissions.ADMIN}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Notifications", "Powiadomienia", currentLang), "#", new Permissions[]{Permissions.ADMIN}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Send notifications", "Wyślij powiadomienia", currentLang), "/admin/notifications/send", new Permissions[]{Permissions.ADMIN}, Collections.emptyList())
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
