package pl.com.chrzanowski.scaffolding.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import pl.com.chrzanowski.scaffolding.api.scaffolding.ScaffUserGetResponse;
import pl.com.chrzanowski.scaffolding.api.scaffolding.ScaffVehicleGetResponse;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.*;
import pl.com.chrzanowski.scaffolding.logic.Language;
import pl.com.chrzanowski.scaffolding.logic.*;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class ApplicationController {
    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    private ScaffUsersService scaffUsersService;
    private DictionariesService dictionariesService;
    private CacheService cacheService;
    private ApplicationConfig applicationConfig;
    private ScaffEmailService emailService;
    private TemplateEngine templateEngine;
    private Environment environment;
    private ScaffEmailConfirmService emailConfirmationService;
    private ScaffVehiclesService vehiclesService;
    private ScaffFuelTypeService fuelTypeService;
    private ScaffDictionariesService scaffDictionariesService;
    private ScaffVehicleTypeService vehicleTypeService;
    private ScaffServiceActionsService serviceActionsService;
    private ScaffServiceActionTypesService serviceActionTypesService;
    private ScaffServiceWorkshopsService workshopsService;

    public ApplicationController(ScaffUsersService scaffUsersService,
                                 DictionariesService dictionariesService,
                                 CacheService cacheService,
                                 ApplicationConfig applicationConfig,
                                 ScaffEmailService emailService,
                                 TemplateEngine templateEngine,
                                 Environment environment,
                                 ScaffEmailConfirmService emailConfirmationService,
                                 ScaffVehiclesService vehiclesService,
                                 ScaffFuelTypeService fuelTypeService,
                                 ScaffVehicleTypeService vehicleTypeService,
                                 ScaffDictionariesService scaffDictionariesService,
                                 ScaffServiceActionsService serviceActionsService,
                                 ScaffServiceActionTypesService serviceActionTypesService,
                                 ScaffServiceWorkshopsService workshopsService) {
        this.scaffUsersService = scaffUsersService;
        this.dictionariesService = dictionariesService;
        this.cacheService = cacheService;
        this.applicationConfig = applicationConfig;
        this.emailService = emailService;
        this.templateEngine = templateEngine;
        this.environment = environment;
        this.emailConfirmationService = emailConfirmationService;
        this.vehiclesService = vehiclesService;
        this.fuelTypeService = fuelTypeService;
        this.scaffDictionariesService = scaffDictionariesService;
        this.vehicleTypeService = vehicleTypeService;
        this.serviceActionsService = serviceActionsService;
        this.serviceActionTypesService = serviceActionTypesService;
        this.workshopsService = workshopsService;
    }
/*
----------------------------------
SCAFFOLDING APP CONTROLLER - BEGIN
----------------------------------
 */

    @GetMapping({"/register"})
    public String register(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES));
        return "register";
    }

    @GetMapping("/confirm-email")
    public String confirmEmail(@RequestParam(name = "token") String tokenValue) {
        emailConfirmationService.confirmEmail(tokenValue);
        return "email-confirmed";
    }

    @RequestMapping(value = "/robots.txt")
    public void robots(HttpServletRequest request, HttpServletResponse response) {

        try {
            String fileContent = "";
            response.getWriter().write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping({"/reset-password"})
    public String passwordReset(Model model, @RequestParam String token) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @GetMapping({"/forget-password"})
    public String forgetPassword() {
        return "forget-password";
    }

    @GetMapping({"/regulation"})
    public String regulation() {
        return "regulation";
    }

    @GetMapping({"/privacy-policy"})
    public String privacyPolicy() {
        return "privacy-policy";
    }

    @GetMapping({"/rodo"})
    public String rodo() {
        return "rodo";
    }

    @GetMapping({"/admin"})
    public String admin() {
        return "admin";
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home-page";
    }

    @GetMapping({"/registered-successfully"})
    public String registeredSuccessfully() {
        return "registered-successfully";
    }

    @GetMapping({"/data-changed-successfully"})
    public String dataChangedSuccessfully() {
        return "data-changed-successfully";
    }


    @GetMapping({"/admin/vehicles"})
    public String adminVehicles(Model model) throws SQLException {

        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicles", vehiclesService.find(new ScaffVehicleFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        model.addAttribute("fuelTypes", fuelTypeService.find(new ScaffFuelTypeFilter()));
        model.addAttribute("vehicleTypes", vehicleTypeService.find(new ScaffVehicleTypeFilter()));

        return "admin-vehicles";
    }


    @GetMapping({"/admin/vehicle/{id}"})
    public String adminVehicleById(@PathVariable Long id, Model model) throws SQLException {

        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicle", new ScaffVehicleGetResponse(vehiclesService.findById(new ScaffVehicleFilter(id))));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        model.addAttribute("fuelTypes", fuelTypeService.find(new ScaffFuelTypeFilter()));
        model.addAttribute("vehicleTypes", vehicleTypeService.find(new ScaffVehicleTypeFilter()));
        return "admin-vehicle";
    }

    @GetMapping({"/admin/vehicle-service-actions/{id}"})
    public String adminVehicleServiceActions(@PathVariable Long id, Model model,
                                             @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                             @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicle", new ScaffVehicleGetResponse(vehiclesService.findById(new ScaffVehicleFilter(id))));
        model.addAttribute("serviceActions", serviceActionsService.find(new ScaffServiceActionsFilter(id, page, pageSize)));
        model.addAttribute("serviceActionTypes", serviceActionTypesService.find(new ScaffServiceActionTypesFilter()));
        model.addAttribute("workshops", workshopsService.find(new ScaffServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-vehicle-service-actions";
    }

    @GetMapping({"/admin/vehicle-service-action/{id}"})
    public String adminVehicleServicesById(@PathVariable Long id, Model model) throws SQLException {
        Long vehicleId = serviceActionsService.findById(new ScaffServiceActionsFilter(id)).getVehicleId();
        if (!serviceActionsService.hasLoggedUserPermissionToActionsManagement()) {
            throw new IllegalArgumentException("Access denied.");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();
        model.addAttribute("vehicle", vehiclesService.findById(new ScaffVehicleFilter(vehicleId)));
        model.addAttribute("serviceAction", serviceActionsService.findById(new ScaffServiceActionsFilter(id)));
        model.addAttribute("serviceActionTypes", serviceActionTypesService.find(new ScaffServiceActionTypesFilter()));
        model.addAttribute("workshops", workshopsService.find(new ScaffServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-vehicle-service-action";
    }

    @GetMapping({"/admin/workshops"})
    public String adminWorkshops(Model model) {

        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("workshops", workshopsService.find(new ScaffServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-workshops";
    }

    @GetMapping({"/admin/workshop/{id}"})
    public String adminWorkshopsById(@PathVariable Long id, Model model) {

        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("workshop", workshopsService.find(new ScaffServiceWorkshopsFilter(id)).get(0));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-workshop";
    }

    @GetMapping({"/admin/service-action-types"})
    public String adminServiceActionTypes(Model model) {

        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("serviceActionTypes", serviceActionTypesService.find(new ScaffServiceActionTypesFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-service-action-types";
    }

    @GetMapping({"/admin/service-action-type/{id}"})
    public String adminServiceActionTypeById(@PathVariable Long id, Model model) {

        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("serviceActionType",
                serviceActionTypesService.find(new ScaffServiceActionTypesFilter(id)).get(0));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-service-action-type";
    }

    
    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @GetMapping({"/admin/login"})
    public String adminLogin() {
        return "login-admin";
    }


    @GetMapping({"/logout"})
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("logout", true);
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        cacheService.invalidateDictionaries();
        log.debug(CacheType.DICTIONARIES + " cache invalidated");
        cacheService.invalidateMenu();
        log.debug(CacheType.MENU + " cache invalidated");
        return "login";
    }

    @GetMapping({"/account-settings"})
    public String myAccount(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES));
        model.addAttribute("user", new ScaffUserGetResponse(scaffUsersService.getLoggedUser()));
        return "my-account";
    }



/*
--------------------------------
SCAFFOLDING APP CONTROLLER - END
--------------------------------
 */


    @GetMapping({"/admin/notifications/send"})
    public String notificationsSending(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
        return "admin-notifications-send";
    }

    @GetMapping({"/admin/marketing/newsletter"})
    public String newsletter() {
        return "admin-marketing-newsletter";
    }

    @GetMapping({"/building"})
    public String building(Model model) {
        return "building";
    }

}
