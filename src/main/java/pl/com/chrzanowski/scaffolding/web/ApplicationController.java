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
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.*;
import pl.com.chrzanowski.scaffolding.logic.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class ApplicationController {
    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    private UserService userService;
    private DictionariesService dictionariesService;
    private CacheService cacheService;
    private ApplicationConfig applicationConfig;
    private EmailService emailService;
    private TemplateEngine templateEngine;
    private Environment environment;
    private EmailConfirmService emailConfirmationService;
    private IVehicle iVehicle;
    private IServiceActions iServiceActions;
    private IServiceActonType iServiceActonType;
    private ServiceWorkshopsService workshopsService;
    private VehiclesBrandsAndModelsService vehiclesBrandsAndModelsService;
    private IVehicleTires iVehicleTires;

    public ApplicationController(UserService userService,
                                 DictionariesService dictionariesService,
                                 CacheService cacheService,
                                 ApplicationConfig applicationConfig,
                                 EmailService emailService,
                                 TemplateEngine templateEngine,
                                 Environment environment,
                                 EmailConfirmService emailConfirmationService,
                                 IVehicle iVehicle,
                                 IServiceActions iServiceActions,
                                 IServiceActonType iServiceActonType,
                                 ServiceWorkshopsService workshopsService,
                                 VehiclesBrandsAndModelsService vehiclesBrandsAndModelsService,
                                 IVehicleTires iVehicleTires) {
        this.userService = userService;
        this.dictionariesService = dictionariesService;
        this.cacheService = cacheService;
        this.applicationConfig = applicationConfig;
        this.emailService = emailService;
        this.templateEngine = templateEngine;
        this.environment = environment;
        this.emailConfirmationService = emailConfirmationService;
        this.iVehicle = iVehicle;
        this.iServiceActonType = iServiceActonType;
        this.workshopsService = workshopsService;
        this.iServiceActions = iServiceActions;
        this.vehiclesBrandsAndModelsService = vehiclesBrandsAndModelsService;
        this.iVehicleTires = iVehicleTires;
    }

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
    public String adminVehicles(Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicles", iVehicle.find(new VehicleFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        model.addAttribute("fuelTypes", dictionariesService.getDictionary(DictionaryType.FUEL_TYPES, lang));
        model.addAttribute("vehicleTypes", dictionariesService.getDictionary(DictionaryType.VEHICLE_TYPES, lang));
        model.addAttribute("brands", dictionariesService.getDictionary(DictionaryType.VEHICLE_BRANDS, lang));
        model.addAttribute("models", dictionariesService.getDictionary(DictionaryType.VEHICLE_MODELS, lang));

        return "admin-vehicles";
    }

    @GetMapping({"/admin/brands-and-models"})
    public String adminBrandsAndModels(Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("brandsAndModels", vehiclesBrandsAndModelsService.find(new VehiclesBrandsAndModelsFilter()));
        model.addAttribute("brands", dictionariesService.getDictionary(DictionaryType.VEHICLE_BRANDS, lang));
        model.addAttribute("models", dictionariesService.getDictionary(DictionaryType.VEHICLE_MODELS, lang));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-brands-and-models";
    }


    @GetMapping({"/admin/vehicle/{id}"})
    public String adminVehicleById(@PathVariable Long id, Model model) throws SQLException {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(id)));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        model.addAttribute("fuelTypes", dictionariesService.getDictionary(DictionaryType.FUEL_TYPES, lang));
        model.addAttribute("vehicleTypes", dictionariesService.getDictionary(DictionaryType.VEHICLE_TYPES, lang));


        return "admin-vehicle";
    }

    @GetMapping({"/admin/vehicle-edit/{id}"})
    public String adminVehicleEditById(@PathVariable Long id, Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(id)));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        model.addAttribute("fuelTypes", dictionariesService.getDictionary(DictionaryType.FUEL_TYPES, lang));
        model.addAttribute("vehicleTypes", dictionariesService.getDictionary(DictionaryType.VEHICLE_TYPES, lang));
        model.addAttribute("brands", dictionariesService.getDictionary(DictionaryType.VEHICLE_BRANDS, lang));
        model.addAttribute("models", dictionariesService.getDictionary(DictionaryType.VEHICLE_MODELS, lang));
        return "admin-vehicle-edit";
    }

    @GetMapping(path = "/admin/tires/{id}")
    public String adminVehicleTires(@PathVariable Long id, Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("tires", iVehicleTires.find(new VehicleTiresFilter(null,id)));
        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(id)));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        return "admin-vehicle-tires";
    }

    @GetMapping(path = "/admin/tire/{id}")
    public String adminVehicleTire(@PathVariable Long id, Model model) {
        Long vehicleId = iVehicleTires.find(new VehicleTiresFilter(id)).get(0).getVehicleId();
        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("tire", iVehicleTires.findById(new VehicleTiresFilter(id)));
        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(vehicleId)));
        model.addAttribute("yesNoDict", dictionariesService.getDictionary(DictionaryType.YES_NO,lang));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
        return "admin-vehicle-tire";
    }

    @GetMapping({"/admin/vehicle-service-actions/{id}"})
    public String adminVehicleServiceActions(@PathVariable Long id, Model model,
                                             @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                             @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(id)));
        model.addAttribute("serviceActions", iServiceActions.find(new ServiceActionsFilter(id, page, pageSize)));
        model.addAttribute("serviceActionTypes", dictionariesService.getDictionary(DictionaryType.SERVICE_ACTION_TYPES, lang));
        model.addAttribute("workshops", workshopsService.find(new ServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-vehicle-service-actions";
    }

    @GetMapping({"/admin/vehicle-service-action/{id}"})
    public String adminVehicleServicesById(@PathVariable Long id, Model model) {
        Long vehicleId = iServiceActions.findById(new ServiceActionsFilter(id)).getVehicleId();
        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied.");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();
        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(vehicleId)));
        model.addAttribute("serviceAction", iServiceActions.findById(new ServiceActionsFilter(id)));
        model.addAttribute("serviceActionTypes", dictionariesService.getDictionary(DictionaryType.SERVICE_ACTION_TYPES, lang));
        model.addAttribute("workshops", workshopsService.find(new ServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-vehicle-service-action";
    }

    @GetMapping({"/admin/vehicle-service-action-edit/{id}"})
    public String adminVehicleServicesEditById(@PathVariable Long id, Model model) {
        Long vehicleId = iServiceActions.findById(new ServiceActionsFilter(id)).getVehicleId();
        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied.");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();
        model.addAttribute("vehicle", iVehicle.findById(new VehicleFilter(vehicleId)));
        model.addAttribute("serviceAction", iServiceActions.findById(new ServiceActionsFilter(id)));
        model.addAttribute("serviceActionTypes", dictionariesService.getDictionary(DictionaryType.SERVICE_ACTION_TYPES, lang));
        model.addAttribute("workshops", workshopsService.find(new ServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-vehicle-service-action-edit";
    }

    @GetMapping({"/admin/workshops"})
    public String adminWorkshops(Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("workshops", workshopsService.find(new ServiceWorkshopsFilter()));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-workshops";
    }

    @GetMapping({"/admin/workshop/{id}"})
    public String adminWorkshopsById(@PathVariable Long id, Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("workshop", workshopsService.find(new ServiceWorkshopsFilter(id)).get(0));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-workshop";
    }

    @GetMapping({"/admin/service-action-types"})
    public String adminServiceActionTypes(Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("serviceActionTypes",
                dictionariesService.getDictionary(DictionaryType.SERVICE_ACTION_TYPES, lang));
        model.addAttribute("languageDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));

        return "admin-service-action-types";
    }

    @GetMapping({"/admin/service-action-type/{id}"})
    public String adminServiceActionTypeById(@PathVariable Long id, Model model) {

        if (!userService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("serviceActionType", iServiceActonType.find(new ServiceActionTypesFilter(id)).get(0));
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
        model.addAttribute("user", userService.getLoggedUser());
        return "my-account";
    }


    @GetMapping({"/admin/notifications/send"})
    public String notificationsSending(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.PL));
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
