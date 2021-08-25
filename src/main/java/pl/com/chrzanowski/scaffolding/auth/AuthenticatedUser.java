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
import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerData;
import pl.com.chrzanowski.scaffolding.logic.ApplicationConfigService;
import pl.com.chrzanowski.scaffolding.logic.CacheType;
import pl.com.chrzanowski.scaffolding.logic.Language;
import pl.com.chrzanowski.scaffolding.logic.UsersService;
import pl.com.chrzanowski.scaffolding.logic.courseplatform.CourseCustomersService;
import pl.com.chrzanowski.scaffolding.logic.courseplatform.CustomerAuthoritiesService;
import pl.com.chrzanowski.scaffolding.logic.courseplatform.CustomerAuthority;
import pl.com.chrzanowski.scaffolding.logic.courseplatform.LanguagesUtil;

import java.util.*;

@Service
public class AuthenticatedUser {
    private UserPermissionsJdbcRepository userPermissionsJdbcRepository;
    private UsersService usersService;
    private ApplicationConfigService applicationConfigService;
    private UserDetailsService userDetailsService;
    private AdviserConfig adviserConfig;
    private CourseCustomersService courseCustomersService;
    private CustomerAuthoritiesService customerAuthoritiesService;

    public AuthenticatedUser(UserPermissionsJdbcRepository userPermissionsJdbcRepository, UsersService usersService, ApplicationConfigService applicationConfigService, UserDetailsService userDetailsService, AdviserConfig adviserConfig, CourseCustomersService courseCustomersService, CustomerAuthoritiesService customerAuthoritiesService) {
        this.userPermissionsJdbcRepository = userPermissionsJdbcRepository;
        this.usersService = usersService;
        this.applicationConfigService = applicationConfigService;
        this.userDetailsService = userDetailsService;
        this.adviserConfig = adviserConfig;
        this.courseCustomersService = courseCustomersService;
        this.customerAuthoritiesService = customerAuthoritiesService;
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

        CustomerData loggedCustomer = courseCustomersService.getLoggedCustomer();
        Language currentLang = LanguagesUtil.getCurrentLanguage();

        if (customerAuthoritiesService.hasCustomerAuthority(loggedCustomer, CustomerAuthority.ADMIN)) {
            return new ArrayList<>(Arrays.asList(
                    new Menu("Course Platform", "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Courses", "Kursy", currentLang), "/admin/courses", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Buy Our Code", "Kup Nasz Kod", currentLang), "/admin/buy-our-code", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Selections", "Selekcje", currentLang), "/admin/selections", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Orders", "Zamówienia", currentLang), "/admin/orders", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Customers", "Klienci", currentLang), "/admin/customers", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Authors", "Autorzy", currentLang), "/admin/authors", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Course Comments", "Komentarze Do Kursów", currentLang), "/admin/course-comments", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Lesson Comments", "Komentarze Do Lekcji", currentLang), "/admin/lesson-comments", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Entertainment", "Rozrywka", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Memes", "Memy", currentLang), "/admin/memes", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Procedures", "Procedury", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Invoice Issuing", "Wystawianie Faktury", currentLang), "/admin/procedure/issue-invoice", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Return (Orders, Courses)", "Zwroty (Zamówienia, Kursy)", currentLang), "/admin/procedure/course-return", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu("Marketing", "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu("Newsletter", "/admin/marketing/newsletter", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Notifications", "Powiadomienia", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Send notifications", "Wyślij powiadomienia", currentLang), "/admin/notifications/send", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Statistics", "Statystyki", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Summary", "Podsumowanie", currentLang), "/admin/statistics/summary", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Top Selling Products (For Money)", "Najlepiej Sprzedające Się Produkty (Płatne)", currentLang), "/admin/statistics/top-selling-money", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Top Selling Products (For Free)", "Najlepiej Sprzedające Się Produkty (Darmowe)", currentLang), "/admin/statistics/summary", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("New Customers", "Nowi Klienci", currentLang), "/admin/statistics/new-customers", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Visited Landings Count", "Licznik Odwiedzin Landingów", currentLang), "/admin/statistics/visited-landings-count", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Courses completion", "Ukończenie kursów", currentLang), "/admin/statistics/courses-completion", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                            //new Menu("Most active customers", "/admin/statistics/most-active-customers", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    ))),
                    new Menu(chooseMenuName("Student Area", "Strefa kursanta", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("To do", "Do zrobienia", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
                    )))
            ));
        } else if (customerAuthoritiesService.hasCustomerAuthority(loggedCustomer, CustomerAuthority.TEACHER)) {
            return new ArrayList<>(Arrays.asList(
                    new Menu(chooseMenuName("Management", "Zarządzanie", currentLang), "#", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, new ArrayList<>(Arrays.asList(
                            new Menu(chooseMenuName("Courses", "Kursy", currentLang), "/admin/courses", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Course Comments", "Komentarze Do Kursów", currentLang), "/admin/course-comments", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList()),
                            new Menu(chooseMenuName("Lesson Comments", "Komentarze Do Lekcji", currentLang), "/admin/lesson-comments", new Permissions[]{Permissions.LIFE_ADVISER_COMMON}, Collections.emptyList())
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
