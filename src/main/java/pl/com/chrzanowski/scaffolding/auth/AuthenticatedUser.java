package pl.com.chrzanowski.scaffolding.auth;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.config.AdviserConfig;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.logic.ApplicationConfigService;
import pl.com.chrzanowski.scaffolding.logic.CacheType;
import pl.com.chrzanowski.scaffolding.logic.Language;
import pl.com.chrzanowski.scaffolding.logic.UsersService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.LanguagesUtil;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUserAuthoritiesService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUserAuthority;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUsersService;

import java.util.*;

@Service
public class AuthenticatedUser {
    private UserPermissionsJdbcRepository userPermissionsJdbcRepository;
    private UsersService usersService;
    private ApplicationConfigService applicationConfigService;
    private UserDetailsService userDetailsService;
    private AdviserConfig adviserConfig;
    private ScaffUserAuthoritiesService scaffUserAuthoritiesService;
    private ScaffUsersService scaffUsersService;

    public AuthenticatedUser(UserPermissionsJdbcRepository userPermissionsJdbcRepository, UsersService usersService,
                             ApplicationConfigService applicationConfigService, UserDetailsService userDetailsService
            , AdviserConfig adviserConfig, ScaffUsersService scaffUsersService,
                             ScaffUserAuthoritiesService scaffUserAuthoritiesService) {
        this.userPermissionsJdbcRepository = userPermissionsJdbcRepository;
        this.usersService = usersService;
        this.applicationConfigService = applicationConfigService;
        this.userDetailsService = userDetailsService;
        this.adviserConfig = adviserConfig;
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

    public boolean isAdviserActive() {
        return adviserConfig.getAdviserActive();
    }

    public Long getUserId() {
        String userName = getUserName();
        UserData user = usersService.getUserByLogin(userName);
        return user.getId();
    }

    public boolean hasAnyPermission(Permissions... permissions) {
        if (hasRole("Super administrator")) {
            return true;
        }
        Set<Permissions> perms = userPermissionsJdbcRepository.getUserPermissions(getUserName());
        for (Permissions elem : permissions) {
            if (perms.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    public void loginAs(String login) {
        UserDetails admin = userDetailsService.loadUserByUsername(login);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Cacheable(CacheType.USER_ROLE)
    public boolean hasRole(String userRole) {
        return userPermissionsJdbcRepository.hasUserRole(getUserName(), userRole);
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
                                    new Menu(chooseMenuName("Vehicle types", "Typy pojazdów", currentLang),
                                            "/admin/vehicle-types", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                                    new Menu(chooseMenuName("Service actions types", "Typy serwisów", currentLang),
                                            "/admin/action-service-types", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
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

    private List<Menu> filterByPermissions(List<Menu> menuItems) {
        List<Menu> filteredMenu = new ArrayList<>();

        for (Menu menu : menuItems) {
            if (hasAnyPermission(menu.getPermissions())) {
                filteredMenu.add(menu);
                if (menu.hasChildren()) {
                    removeUnavailableChildren(menu);
                    for (Menu submenu : menu.getChildren()) {
                        if (submenu.hasChildren()) {
                            removeUnavailableChildren(submenu);
                        }
                    }
                }
            }
        }
        return filteredMenu;
    }

    private void removeUnavailableChildren(Menu menu) {
        menu.getChildren().removeAll(getUnavailableMenus(menu));
    }

    private List<Menu> getUnavailableMenus(Menu menu) {
        List<Menu> submenusToRemove = new ArrayList<>();
        for (Menu submenu : menu.getChildren()) {
            if (!hasAnyPermission(submenu.getPermissions())) {
                submenusToRemove.add(submenu);
            }
        }
        return submenusToRemove;
    }
}
