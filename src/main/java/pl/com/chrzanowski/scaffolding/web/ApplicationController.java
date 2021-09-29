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
import pl.com.chrzanowski.scaffolding.auth.AuthenticatedUser;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.*;
import pl.com.chrzanowski.scaffolding.logic.Language;
import pl.com.chrzanowski.scaffolding.logic.*;
import pl.com.chrzanowski.scaffolding.logic.adviser.AccountsService;
import pl.com.chrzanowski.scaffolding.logic.adviser.AdviserService;
import pl.com.chrzanowski.scaffolding.logic.adviser.ApplicationsService;
import pl.com.chrzanowski.scaffolding.logic.adviser.ContextConfigsService;
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
    private OrdersService ordersService;
    private CustomersService customersService;
    private DictionariesService dictionariesService;
    private ProductsService productsService;
    private CacheService cacheService;
    private AuthenticatedUser authenticatedUser;
    private GalleryService galleryService;
    private MenuService menuService;
    private AdviserService adviserService;
    private AccountsService accountsService;
    private ApplicationsService applicationsService;
    private ContextConfigsService contextConfigsService;
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
                                 OrdersService ordersService,
                                 CustomersService customersService,
                                 DictionariesService dictionariesService,
                                 ProductsService productsService,
                                 CacheService cacheService,
                                 AuthenticatedUser authenticatedUser,
                                 GalleryService galleryService,
                                 MenuService menuService,
                                 AdviserService adviserService,
                                 AccountsService accountsService,
                                 ApplicationsService applicationsService,
                                 ContextConfigsService contextConfigsService,
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
        this.ordersService = ordersService;
        this.customersService = customersService;
        this.dictionariesService = dictionariesService;
        this.productsService = productsService;
        this.cacheService = cacheService;
        this.authenticatedUser = authenticatedUser;
        this.galleryService = galleryService;
        this.menuService = menuService;
        this.adviserService = adviserService;
        this.accountsService = accountsService;
        this.applicationsService = applicationsService;
        this.contextConfigsService = contextConfigsService;
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
                                             @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException
    {
        if (!scaffUsersService.isLoggedUserAdmin()) {
            throw new IllegalArgumentException("Access denied");
        }

        Language lang = LanguagesUtil.getCurrentLanguage();

        model.addAttribute("vehicle", new ScaffVehicleGetResponse(vehiclesService.findById(new ScaffVehicleFilter(id))));
        model.addAttribute("serviceActions", serviceActionsService.find(new ScaffServiceActionsFilter(id, page,pageSize)));
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
        model.addAttribute("vehicle",vehiclesService.findById(new ScaffVehicleFilter(vehicleId)));
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




    //    @GetMapping({"/admin/course/{id}"})
//    public String course(@PathVariable Long id, Model model) {
//
//        if (!coursesService.hasLoggedCustomerPermissionsToCourseManagement(id)) {
//            throw new IllegalArgumentException("Access denied");
//        }
//
//        Language lang = LanguagesUtil.getCurrentLanguage();
//
//        model.addAttribute("course", new CourseGetResponse(coursesService.find(new CoursesFilter(id)).get(0)));
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, lang));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, lang));
//        model.addAttribute("lessonVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.LESSON_VISIBILITY_STATUSES, lang));
//        model.addAttribute("moduleVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.MODULE_VISIBILITY_STATUSES, lang));
//        model.addAttribute("courseSaleStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_SALE_STATUSES, lang));
//        model.addAttribute("movieLinkTypesDict", dictionariesService.getDictionary(DictionaryType.MOVIE_LINK_TYPES, lang));
//        return "course";
//    }


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

//
//    @GetMapping("/mentoring")
//    public String mentoring() {
//        return "landing/landing-mentoring";
//    }
//
//    @GetMapping("/admin/lesson-comments")
//    public String lessonComments(Model model) {
//        model.addAttribute("lessonCommentStatusesDict", dictionariesService.getDictionary(DictionaryType.LESSON_COMMENT_STATUSES, LanguagesUtil.getCurrentLanguage()));
//        return "lesson-comments";
//    }
//
//    @GetMapping("/admin/course-comments")
//    public String courseComments(Model model) {
//        model.addAttribute("courseCommentVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_COMMENT_VISIBILITY_STATUSES, LanguagesUtil.getCurrentLanguage()));
//        return "course-comments";
//    }
//
//    @GetMapping({"/memes"})
//    public String memesList() {
//        return "memes-list";
//    }
//
//    @GetMapping({"/meme/{code}"})
//    public String memePage(Model model, @PathVariable String code) {
//        MemeData meme = memesService.findByCode(code);
//        model.addAttribute("meme", new MemeGetResponse(meme));
//        return "meme-page";
//    }
//
//    @GetMapping({"/admin/memes"})
//    public String memes(Model model) {
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
//        return "memes";
//    }

//    @GetMapping({"/admin/meme/{id}"})
//    public String meme(Model model, @PathVariable Long id) {
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
//        model.addAttribute("meme", new MemeGetResponse(memesService.find(new MemesFilter(id)).get(0)));
//        return "meme";
//    }


//    @GetMapping(
//            value = "/get-image/{name}",
//            produces = MediaType.IMAGE_GIF_VALUE
//    )
//    public @ResponseBody
//    byte[] getImage(@PathVariable String name) {
//        return filesService.getImage(name);
//    }
//
//    @GetMapping(
//            value = "/meme/file/{name}",
//            produces = MediaType.IMAGE_GIF_VALUE
//    )
//    public @ResponseBody
//    byte[] getMemeFile(@PathVariable(name = "name") String fileName) {
//        return filesService.getMemeFile(fileName);
//    }

//    @GetMapping(
//            value = "/get-invoice/{id}",
//            produces = MediaType.APPLICATION_PDF_VALUE
//    )
//    public @ResponseBody
//    byte[] getInvoice(@PathVariable Long id) {
//
//        InvoiceData invoice = invoicesService.find(new InvoicesFilter(id, null, false)).get(0);
//
//        if (!courseCustomersService.getLoggedCustomer().getId().equals(invoice.getOrder().getCustomer().getId())) {
//            throw new IllegalArgumentException("Access denied");
//        }
//
//        return filesService.getInvoice(invoice.getFileName());
//
//    }
//
//    @GetMapping(value = "/download/course/attachment/{id}")
//    public @ResponseBody
//    byte[] downloadCourseAttachment(@PathVariable Long id) {
//        return courseAttachmentsService.getFile(id);
//    }
//
//    @GetMapping(value = "/download/lesson/attachment/{lessonAttachmentId}")
//    public @ResponseBody
//    byte[] downloadLessonAttachment(@PathVariable Long lessonAttachmentId) {
//        return lessonAttachmentsService.getFile(lessonAttachmentId);
//    }

//    @GetMapping(
//            value = "/admin/get-invoice/{id}",
//            produces = MediaType.APPLICATION_PDF_VALUE
//    )
//    public @ResponseBody
//    byte[] getInvoiceAdmin(@PathVariable Long id) {
//        InvoiceData invoice = invoicesService.find(new InvoicesFilter(id, null)).get(0);
//        return filesService.getInvoice(invoice.getFileName());
//    }
//
//    @GetMapping({"/payments"})
//    public String payments(Model model) {
//        model.addAttribute("ordersStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_ORDER_STATUSES));
//        model.addAttribute("invoiceTypesDict", dictionariesService.getDictionary(DictionaryType.INVOICE_TYPES));
//        return "payments";
//    }
//
//    @GetMapping({"/courses"})
//    public String courses(Model model) {
//        model.addAttribute("pathToGallery", applicationConfig.getCoursePathToGallery());
//        return "home";
//    }
//

//    @GetMapping({"/buy-our-code"})
//    public String buyOurCode(Model model) {
//        model.addAttribute("pathToGallery", applicationConfig.getCoursePathToGallery());
//        return "buy-our-code-public";
//    }
//
//    @GetMapping({"/thanks-for-shopping"})
//    public String thanksForShopping() {
//        return "thanks-for-shopping";
//    }
//
//
//
//    @GetMapping({"/register-teacher"})
//    public String registerTeacher(Model model) {
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES));
//        return "register-teacher";
//    }
//
//    @GetMapping({"/admin/procedure/course-return"})
//    public String procedureCourseReturn() {
//        return "procedure-course-return";
//    }
//
//    @GetMapping({"/admin/procedure/issue-invoice"})
//    public String procedureIssueInvoice() {
//        return "procedure-issue-invoice";
//    }

//    @GetMapping({"/basket"})
//    public String basket(Model model) {
//        CustomerData customer = courseCustomersService.getLoggedCustomer();
//        if (customer != null) {
//            model.addAttribute("loggedCustomer", new CustomerGetResponse(customer));
//        }
//        model.addAttribute("pathToGallery", applicationConfig.getCoursePathToGallery());
//        model.addAttribute("countriesDict", dictionariesService.getDictionary(DictionaryType.COUNTRIES));
//        return "basket";
//    }


//    @GetMapping({"/my-courses"})
//    public String myCourses() {
//        return "my-courses";
//    }
//
//    @GetMapping({"/my-codes"})
//    public String myCodes() {
//        return "my-codes";
//    }
//
//    @GetMapping({"/my-selections"})
//    public String mySelections() {
//        return "my-selections";
//    }
//
//    @GetMapping({"/selections"})
//    public String selections() {
//        return "selections";
//    }

//    @GetMapping({"/selection/{code}"})
//    public String selection(@PathVariable String code, Model model) {
//        CourseData course = coursesService.find(new CoursesFilter(code)).get(0);
//        model.addAttribute("course", new CourseGetResponse(course));
//        model.addAttribute("languageFromDict", dictionariesService.getDictionaryElementByCode(course.getLanguage(), DictionaryType.LANGUAGES));
//        return "selection";
//    }
//
//    @GetMapping({"/admin/selections"})
//    public String adminSelections(Model model) {
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, Language.US));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, Language.US));
//        return "selections-admin";
//    }

//    @GetMapping({"/admin/selection/{id}"})
//    public String adminSelection(Model model, @PathVariable Long id) {
//
//        if (!coursesService.hasLoggedCustomerPermissionsToCourseManagement(id)) {
//            throw new IllegalArgumentException("Access denied");
//        }
//
//        model.addAttribute("course", new CourseGetResponse(coursesService.find(new CoursesFilter(id)).get(0)));
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, Language.US));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, Language.US));
//        model.addAttribute("lessonVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.LESSON_VISIBILITY_STATUSES, Language.US));
//        model.addAttribute("courseSaleStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_SALE_STATUSES, Language.US));
//        model.addAttribute("movieLinkTypesDict", dictionariesService.getDictionary(DictionaryType.MOVIE_LINK_TYPES, Language.US));
//
//        return "selection-admin";
//
//    }

//    @GetMapping({"/watch/course/{courseId}"})
//    public String watchCourse(@PathVariable Long courseId, Model model) {
//
//        for (CourseData course : coursesService.findCoursesOwnedByLoggedCustomer()) {
//            if (course.getId().equals(courseId)) {
//
//                CustomerData loggedCustomer = courseCustomersService.getLoggedCustomer();
//
//                if (loggedCustomer == null) {
//                    throw new IllegalArgumentException("Your are logged out. Please log in and retry");
//                }
//
//                if (!hasUserCourse(courseId, loggedCustomer)) {
//                    throw new IllegalArgumentException("Access denied");
//                }
//
//                List<LessonData> lessonsWithoutModule = lessonsService.findWithCustomerActivity(courseId, loggedCustomer, true, null);
//                List<ModuleData> modulesInCourse = modulesService.find(new ModulesFilter(courseId, false, ModuleVisibilityStatus.VISIBLE));
//
//                List<ModuleGetResponse> modulesInCourseResponses = new ArrayList<>();
//
//                for (ModuleData moduleInCourse : modulesInCourse) {
//                    List<LessonData> lessons = lessonsService.findWithCustomerActivity(courseId, loggedCustomer, null, moduleInCourse.getId());
//                    modulesInCourseResponses.add(new ModuleGetResponse(moduleInCourse, lessonsToResponses(lessons)));
//                }
//
//                model.addAttribute("lessonsWithoutModules", lessonsToResponses(lessonsWithoutModule));
//                model.addAttribute("modules", modulesInCourseResponses);
//                model.addAttribute("course", new CourseGetResponse(coursesService.find(new CoursesFilter(courseId)).get(0)));
//
//                return "watch";
//            }
//        }
//
//        return "error";
//    }

//    private Boolean hasUserCourse(Long courseId, CustomerData customer) {
//        List<CourseData> ownedCourses = coursesService.findCoursesOwnedByCustomer(customer);
//        for (CourseData ownedCourse : ownedCourses) {
//            if (ownedCourse.getId().equals(courseId)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private List<LessonGetResponse> lessonsToResponses(List<LessonData> lessons) {
//        List<LessonGetResponse> list = new ArrayList<>();
//        for (LessonData lesson : lessons) {
//            list.add(new LessonGetResponse(lesson));
//        }
//        return list;
//    }

//    @GetMapping({"/course-details/{id}"})
//    public String courseDetails(@PathVariable Long id, Model model) {
//
//        CourseData course = coursesService.find(new CoursesFilter(id)).get(0);
//
//        if (course.getCode() == null || course.getCode().equals("")) {
//            model.addAttribute("course", new CourseGetResponse(course));
//            model.addAttribute("languageFromDict", dictionariesService.getDictionaryElementByCode(course.getLanguage(), DictionaryType.LANGUAGES));
//            return "course-details";
//        } else {
//            return "redirect:/course/" + course.getCode();
//        }
//
//    }
//
//    @GetMapping({"/course/{code}"})
//    public String course(@PathVariable String code, Model model) {
//        CourseData course = coursesService.find(new CoursesFilter(code)).get(0);
//        model.addAttribute("course", new CourseGetResponse(course));
//        model.addAttribute("languageFromDict", dictionariesService.getDictionaryElementByCode(course.getLanguage(), DictionaryType.LANGUAGES));
//        return "course-details";
//    }

//    @GetMapping({"/buy-our-code/{code}"})
//    public String buyOurCode(@PathVariable String code, Model model) {
//        CourseData course = coursesService.find(new CoursesFilter(code)).get(0);
//        model.addAttribute("course", new CourseGetResponse(course));
//        model.addAttribute("languageFromDict", dictionariesService.getDictionaryElementByCode(course.getLanguage(), DictionaryType.LANGUAGES));
//        return "course-details";
//    }
//
//    @PostMapping("/admin/update/image/course")
//    public String uploadImage(@RequestParam(name = "id") Long courseId, @RequestParam("imageFile") MultipartFile imageFile) {
//        coursesService.updateAndSaveImage(courseId, imageFile);
//        return "redirect:/admin/course/" + courseId;
//    }
//
//    @PostMapping("/admin/upload-invoice")
//    public String uploadInvoice(@RequestParam(name = "orderId") Long orderId,
//                                @RequestParam(name = "invoiceFile") MultipartFile invoiceFile,
//                                @RequestParam(name = "invoiceType") String invoiceType,
//                                @RequestParam(name = "invoiceNumber") String invoiceNumber
//    ) {
//        invoicesService.addInvoiceToOrder(orderId, invoiceFile, new InvoiceData(invoiceType, invoiceNumber));
//        return "redirect:/admin/order/" + orderId;
//    }
//
//    @GetMapping({"/admin/courses"})
//    public String adminCourses(Model model) {
//        Language currentLang = LanguagesUtil.getCurrentLanguage();
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, currentLang));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, currentLang));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, currentLang));
//        return "courses";
//    }
//
//    @GetMapping({"/admin/buy-our-code"})
//    public String buyOurCodeAdmin(Model model) {
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, Language.US));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, Language.US));
//        return "buy-our-code";
//    }
//
//    @GetMapping({"/admin/orders"})
//    public String orders(Model model) {
//        model.addAttribute("ordersStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_ORDER_STATUSES, Language.US));
//        model.addAttribute("yesNoDict", dictionariesService.getDictionary(DictionaryType.YES_NO, Language.US));
//        return "course-orders";
//    }

//    @GetMapping({"/admin/authors"})
//    public String authors() {
//        return "authors";
//    }

    @GetMapping({"/admin/statistics/summary"})
    public String statistics() {
        return "admin-statistics-summary";
    }

    @GetMapping({"/admin/marketing/newsletter"})
    public String newsletter() {
        return "admin-marketing-newsletter";
    }

    @GetMapping({"/admin/notifications/send"})
    public String notificationsSending(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
        return "admin-notifications-send";
    }

    @GetMapping({"/admin/statistics/top-selling-money"})
    public String topSellingMoney() {
        return "admin-statistics-top-selling-money";
    }

//    @GetMapping({"/admin/customers"})
//    public String adminCustomers(Model model) {
//        Language currentLang = LanguagesUtil.getCurrentLanguage();
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, currentLang));
//        model.addAttribute("yesNoDict", dictionariesService.getDictionary(DictionaryType.YES_NO, currentLang));
//        model.addAttribute("authoritiesDict", dictionariesService.getDictionary(DictionaryType.CUSTOMER_AUTHORITIES, currentLang));
//
//        return "customers-admin";
//    }

//    @GetMapping({"/admin/customer/{id}"})
//    public String customer(Model model, @PathVariable Long id) {
//        Language currentLang = LanguagesUtil.getCurrentLanguage();
//        model.addAttribute("customer", new CustomerGetResponse(courseCustomersService.findWithAuthorities(new CustomersFilter(id)).get(0)));
//        model.addAttribute("authoritiesDict", dictionariesService.getDictionary(DictionaryType.CUSTOMER_AUTHORITIES, currentLang));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, currentLang));
//        model.addAttribute("yesNoDict", dictionariesService.getDictionary(DictionaryType.YES_NO, currentLang));
//        model.addAttribute("invoiceTypesDict", dictionariesService.getDictionary(DictionaryType.INVOICE_TYPES, currentLang));
//        model.addAttribute("countriesDict", dictionariesService.getDictionary(DictionaryType.COUNTRIES, currentLang));
//        return "customer-admin";
//    }

    @GetMapping({"/admin/statistics/new-customers"})
    public String newCustomers() {
        return "admin-statistics-new-customers";
    }

    @GetMapping({"/admin/statistics/visited-landings-count"})
    public String visitedLandingsCount() {
        return "admin-statistics-visited-landings-count";
    }

    @GetMapping({"/admin/statistics/courses-completion"})
    public String courseCompletion() {
        return "admin-statistics-completion";
    }

//    @GetMapping({"/admin/author/{id}"})
//    public String author(Model model, @PathVariable Long id) {
//        model.addAttribute("author", new AuthorGetResponse(authorsService.find(new AuthorsFilter(id)).get(0)));
//        return "author";
//    }
//
//    @GetMapping({"/admin/order/{id}"})
//    public String order(Model model, @PathVariable Long id) {
//        model.addAttribute("order", new CourseOrderAdminGetResponse(courseOrdersService.find(new CourseOrdersFilter(id, null)).get(0)));
//        model.addAttribute("billingTypesDict", dictionariesService.getDictionary(DictionaryType.BILLING_TYPES, Language.US));
//        model.addAttribute("orderStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_ORDER_STATUSES, Language.US));
//        model.addAttribute("invoiceTypesDict", dictionariesService.getDictionary(DictionaryType.INVOICE_TYPES, Language.US));
//        return "course-order";
//    }
//
//    @GetMapping({"/admin/course/{id}"})
//    public String course(@PathVariable Long id, Model model) {
//
//        if (!coursesService.hasLoggedCustomerPermissionsToCourseManagement(id)) {
//            throw new IllegalArgumentException("Access denied");
//        }
//
//        Language lang = LanguagesUtil.getCurrentLanguage();
//
//        model.addAttribute("course", new CourseGetResponse(coursesService.find(new CoursesFilter(id)).get(0)));
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, lang));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, lang));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, lang));
//        model.addAttribute("lessonVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.LESSON_VISIBILITY_STATUSES, lang));
//        model.addAttribute("moduleVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.MODULE_VISIBILITY_STATUSES, lang));
//        model.addAttribute("courseSaleStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_SALE_STATUSES, lang));
//        model.addAttribute("movieLinkTypesDict", dictionariesService.getDictionary(DictionaryType.MOVIE_LINK_TYPES, lang));
//        return "course";
//    }

//    @GetMapping({"/admin/buy-our-code/details/{id}"})
//    public String buyOurCodeDetails(@PathVariable Long id, Model model) {
//
//        if (!coursesService.hasLoggedCustomerPermissionsToCourseManagement(id)) {
//            throw new IllegalArgumentException("Access denied");
//        }
//
//        model.addAttribute("course", new CourseGetResponse(coursesService.find(new CoursesFilter(id)).get(0)));
//        model.addAttribute("authorsDict", dictionariesService.getDictionary(DictionaryType.AUTHORS, Language.US));
//        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, Language.US));
//        model.addAttribute("courseVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_VISIBILITY_STATUSES, Language.US));
//        model.addAttribute("lessonVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.LESSON_VISIBILITY_STATUSES, Language.US));
//        model.addAttribute("courseSaleStatusesDict", dictionariesService.getDictionary(DictionaryType.COURSE_SALE_STATUSES, Language.US));
//        model.addAttribute("movieLinkTypesDict", dictionariesService.getDictionary(DictionaryType.MOVIE_LINK_TYPES, Language.US));
//        return "buy-our-code-details";
//    }
//
//    @GetMapping({"/admin/lesson/{id}"})
//    public String lesson(@PathVariable Long id, Model model) {
//        LessonData lesson = lessonsService.find(new LessonsFilter(id)).get(0);
//        Language language = LanguagesUtil.getCurrentLanguage();
//        model.addAttribute("lesson", new LessonGetResponse(lesson));
//        model.addAttribute("lessonVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.LESSON_VISIBILITY_STATUSES, language));
//        model.addAttribute("modulesInCourse", ConverterToResponsesUtil.modulesToResponses(modulesService.find(new ModulesFilter(lesson.getCourse().getId(), false))));
//        model.addAttribute("lessonTypesDict", dictionariesService.getDictionary(DictionaryType.LESSON_TYPES, language));
//        model.addAttribute("movieLinkTypesDict", dictionariesService.getDictionary(DictionaryType.MOVIE_LINK_TYPES, language));
//        return "lesson";
//    }
//
//    @GetMapping({"/admin/module/{id}"})
//    public String module(@PathVariable Long id, Model model) {
//        ModuleData module = modulesService.get(id);
//        model.addAttribute("moduleVisibilityStatusesDict", dictionariesService.getDictionary(DictionaryType.MODULE_VISIBILITY_STATUSES, LanguagesUtil.getCurrentLanguage()));
//        model.addAttribute("module", new ModuleGetResponse(module));
//        return "module";
//    }

    @GetMapping({"/building"})
    public String building(Model model) {
        return "building";
    }

//    @GetMapping({"/orders"})
//    public String orders(Model model,
//                         @RequestParam(name = "first_and_last_name", required = false) String firstAndLastName,
//                         @RequestParam(name = "order_date_from", required = false) String orderDateFrom,
//                         @RequestParam(name = "order_date_to", required = false) String orderDateTo,
//                         @RequestParam(name = "payment_status_id", required = false) Long paymentStatusId,
//                         @RequestParam(name = "order_id", required = false) String orderId,
//                         @RequestParam(name = "discount_code", required = false) String discountCode,
//                         @RequestParam(name = "payment_method_id", required = false) Long paymentMethodId,
//                         @RequestParam(name = "driver_id", required = false) Long driverId,
//                         @RequestParam(name = "invoice", required = false) String invoice,
//                         @RequestParam(name = "delivery_date_from", required = false) String deliveryDateFrom,
//                         @RequestParam(name = "delivery_date_to", required = false) String deliveryDateTo,
//                         @RequestParam(name = "delivery_method_id", required = false) Long deliveryMethodId,
//                         @RequestParam(name = "diet_id", required = false) Long dietId,
//                         @RequestParam(name = "order_status_id", required = false) Long orderStatusId,
//                         @RequestParam(name = "orders_finishing_in_days", required = false) Long ordersFinishingInDays,
//                         @RequestParam(name = "city", required = false) String city,
//                         @RequestParam(name = "marked_as_paid_date_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate markedAsPaidDateFrom,
//                         @RequestParam(name = "marked_as_paid_date_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate markedAsPaidDateTo,
//                         @RequestParam(name = "page", required = false) Long page,
//                         @RequestParam(name = "page_size", required = false, defaultValue = "100") Long pageSize
//    ) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ORDERS})) {
//            return "denied";
//        }
//        OrdersFilter filter = prepareFilterForOrders(
//                firstAndLastName,
//                orderDateFrom,
//                orderDateTo,
//                paymentStatusId,
//                orderId,
//                discountCode,
//                paymentMethodId,
//                driverId,
//                "1".equalsIgnoreCase(invoice),
//                deliveryDateFrom,
//                deliveryDateTo,
//                deliveryMethodId,
//                dietId,
//                orderStatusId,
//                ordersFinishingInDays,
//                city,
//                markedAsPaidDateFrom,
//                markedAsPaidDateTo
//        );
//        model.addAttribute("paymentStatuses", dictionariesService.getDictionary(DictionaryType.PAYMENT_STATUSES, Language.PL));
//        model.addAttribute("paymentMethods", dictionariesService.getDictionary(DictionaryType.ORDER_PAYMENT_METHODS, Language.PL));
//        model.addAttribute("drivers", dictionariesService.getDictionary(DictionaryType.DRIVERS, Language.PL));
//        model.addAttribute("shipmentTypes", dictionariesService.getDictionary(DictionaryType.SHIPMENT_TYPES, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//        model.addAttribute("orderStatuses", dictionariesService.getDictionary(DictionaryType.ORDER_STATUSES, Language.PL));
//        model.addAttribute("cities", dictionariesService.getDictionary(DictionaryType.CITIES, Language.PL));
//        model.addAttribute("yesNo", dictionariesService.getDictionary(DictionaryType.YES_NO, Language.PL));
//
//        page = initPageWhenNotSet(page, 1);
//
//        model.addAttribute("selectedPage", page);
//        model.addAttribute("selectedPageSize", pageSize);
//
//        return "orders";
//    }
//
//    private OrdersFilter prepareFilterForOrders(String firstAndLastName,
//                                                String orderDateFrom,
//                                                String orderDateTo,
//                                                Long paymentStatusId,
//                                                String orderId,
//                                                String discountCode,
//                                                Long paymentMethodId,
//                                                Long driverId,
//                                                Boolean invoice,
//                                                String deliveryDateFrom,
//                                                String deliveryDateTo,
//                                                Long deliveryMethodId,
//                                                Long dietId,
//                                                Long orderStatusId,
//                                                Long ordersFinishingInDays,
//                                                String city,
//                                                LocalDate markedAsPaidDateFrom,
//                                                LocalDate markedAsPaidDateTo) {
//        LocalDate ordDateFrom = null;
//        LocalDate ordDateTo = null;
//        if (orderDateFrom == null || orderDateFrom.isEmpty()) {
//            ordDateFrom = LocalDate.now();
//        } else {
//            ordDateFrom = LocalDate.parse(orderDateFrom);
//        }
//        if (orderDateTo != null && !orderDateTo.isEmpty()) {
//            ordDateTo = LocalDate.parse(orderDateFrom);
//        } else {
//            ordDateTo = null;
//        }
//        LocalDate deliveryDtFrom = null;
//        if (deliveryDateFrom != null && !deliveryDateFrom.isEmpty()) {
//            deliveryDtFrom = LocalDate.parse(deliveryDateFrom);
//        }
//        LocalDate deliveryDtTo = null;
//        if (deliveryDateTo != null && !deliveryDateTo.isEmpty()) {
//            deliveryDtTo = LocalDate.parse(deliveryDateTo);
//        }
//        return new OrdersFilter(ordDateFrom, ordDateTo, firstAndLastName, deliveryDtFrom, deliveryDtTo, paymentStatusId, discountCode, paymentMethodId, driverId, deliveryMethodId, dietId, orderStatusId, ordersFinishingInDays, city, markedAsPaidDateFrom, markedAsPaidDateTo, invoice, orderId, null);
//    }
//
//    @GetMapping({"/order"})
//    public String order(@RequestParam(name = "id") Long id, @RequestParam(name = "read_only", required = false, defaultValue = "false") boolean readOnly, Model model, HttpServletRequest request) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ORDERS})) {
//            return "denied";
//        }
//        long startTime = System.currentTimeMillis();
//        model.addAttribute("orderStatuses", dictionariesService.getDictionary(DictionaryType.ORDER_STATUSES, Language.PL));
//        model.addAttribute("yesNo", dictionariesService.getDictionary(DictionaryType.YES_NO, Language.PL));
//        model.addAttribute("paymentStatuses", dictionariesService.getDictionary(DictionaryType.PAYMENT_STATUSES, Language.PL));
//        model.addAttribute("paymentMethods", dictionariesService.getDictionary(DictionaryType.ORDER_PAYMENT_METHODS, Language.PL));
//        model.addAttribute("paymentPaymentMethods", dictionariesService.getDictionary(DictionaryType.PAYMENT_PAYMENT_METHODS, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//        model.addAttribute("shipmentTypes", dictionariesService.getDictionary(DictionaryType.SHIPMENT_TYPES, Language.PL));
//        model.addAttribute("cities", dictionariesService.getDictionary(DictionaryType.CITIES, Language.PL));
//        model.addAttribute("weekendOptions", dictionariesService.getDictionary(DictionaryType.WEEKEND_OPTIONS, Language.PL));
//
//        OrderDetailsData data = ordersService.getOrder(id);
//        model.addAttribute("data", data);
//        model.addAttribute("customer", data.getCustomerForOrder());
//        model.addAttribute("weekendAddress", data.getOrderWeekendAddress());
//        model.addAttribute("invoice", data.getOrderInvoice());
//
//        model.addAttribute("paymentResult", ordersService.getPaymentResultForOrder(id, data.getOrderBasketSum()));
//        model.addAttribute("productResult", ordersService.getProductResultForOrder(id, data.getOrderBasketSumNo(), data.getOrderBasketSum()));
//        model.addAttribute("deliveries", ordersService.getDeliveryDataForOrder(id, data));
//        model.addAttribute("changes", ordersService.getChangesForOrder(id));
//        model.addAttribute("deliveryChanges", ordersService.getDeliveryChangesForOrder(id));
//        model.addAttribute("emails", ordersService.findOrderSentEmails(id));
//        long timeTaken = System.currentTimeMillis() - startTime;
//        log.info("Time Taken by {} is {}", "ApplicationController.order", timeTaken);
//
//        if (!readOnly && !authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ORDERS})) {
//            readOnly = true;
//        }
//        model.addAttribute("readOnly", readOnly);
//
////        System.out.println("referrer: " + request.getHeader("referrer"));
////        System.out.println("getPathInfo: " + request.getPathInfo());
////        System.out.println("getContextPath: " + request.getContextPath());
////        System.out.println("getPathTranslated: " + request.getPathTranslated());
////        System.out.println("getRequestURI: " + request.getRequestURI());
////        System.out.println("getServletPath: " + request.getServletPath());
////        System.out.println("referrer: " + request.getHeader("X-Forwarded-Referrer"));
//
//
//        return "order";
//    }
//
//    @GetMapping({"/orders-short"})
//    public String ordersShort(Model model,
//                              @RequestParam(name = "first_name", required = false) String firstName,
//                              @RequestParam(name = "last_name", required = false) String lastName,
//                              @RequestParam(name = "order_id", required = false) String orderId,
//                              @RequestParam(name = "page", required = false) Long page,
//                              @RequestParam(name = "page_size", required = false, defaultValue = "100") Long pageSize
//    ) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.SIMPLIFIED_ORDERS})) {
//            return "denied";
//        }
//        model.addAttribute("shipmentTypes", dictionariesService.getDictionary(DictionaryType.SHIPMENT_TYPES, Language.PL));
//        model.addAttribute("paymentMethods", dictionariesService.getDictionary(DictionaryType.ORDER_PAYMENT_METHODS, Language.PL));
//        model.addAttribute("paymentStatuses", dictionariesService.getDictionary(DictionaryType.PAYMENT_STATUSES, Language.PL));
//        model.addAttribute("orderStatuses", dictionariesService.getDictionary(DictionaryType.ORDER_STATUSES, Language.PL));
//        model.addAttribute("yesNo", dictionariesService.getDictionary(DictionaryType.YES_NO, Language.PL));
//
//        page = initPageWhenNotSet(page, 1);
//
//        model.addAttribute("list", ordersService.findOrdersShort(new OrdersFilter(firstName, lastName, orderId), page, pageSize));
//        model.addAttribute("selectedPage", page);
//        model.addAttribute("selectedPageSize", pageSize);
//        model.addAttribute("firstName", firstName);
//        model.addAttribute("lastName", lastName);
//        model.addAttribute("orderId", orderId);
//
//
//        return "orders-short";
//    }

//
//    @GetMapping({"/products-demand"})
//    public String productsDemand(Model model,
//                                 @RequestParam(name = "dateAt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateAt,
//                                 @RequestParam(name = "page", required = false) Long page,
//                                 @RequestParam(name = "page_size", required = false, defaultValue = "100") Long pageSize
//    ) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.DELIVERY_REQUEST})) {
//            return "denied";
//        }
//        if (dateAt == null) {
//            dateAt = LocalDate.now();
//        }
//        page = initPageWhenNotSet(page, 1);
//
//        ProductDemandResultData result = productsService.findProductDemandsWithSum(dateAt, page, pageSize);
//        model.addAttribute("list", result.getProducts());
//        model.addAttribute("sum", result.getSum());
//
//        model.addAttribute("selectedDate", dateAt.format(DateTimeFormatter.ISO_DATE)); //TODO if no parameter - today's date
//        model.addAttribute("selectedPage", page);
//        model.addAttribute("selectedPageSize", pageSize);
//        return "products-demand";
//    }
//
//    @GetMapping({"/test-sets"})
//    public String testSets(Model model) {
//        return "test-sets";
//    }
//
//    @GetMapping({"/statistics"})
//    public String statistics(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.DELIVERY_VALUE})) {
//            return "denied";
//        }
//        model.addAttribute("paymentStatuses", dictionariesService.getDictionary(DictionaryType.PAYMENT_STATUSES, Language.PL));
//        model.addAttribute("paymentMethods", dictionariesService.getDictionary(DictionaryType.ORDER_PAYMENT_METHODS, Language.PL));
//        model.addAttribute("shipmentTypes", dictionariesService.getDictionary(DictionaryType.SHIPMENT_TYPES, Language.PL));
//        model.addAttribute("drivers", dictionariesService.getDictionary(DictionaryType.DRIVERS, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//
//        model.addAttribute("defaultStartDate", LocalDate.now());
//        model.addAttribute("defaultEndDate", LocalDate.now());
//
//        return "statistics";
//    }
//
//    @GetMapping({"/menu-preview"})
//    public String menuPreview(Model model
//    ) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.MENU_PREVIEW})) {
//            return "denied";
//        }
////        model.addAttribute("paymentStatuses", dictionariesService.getDictionary(DictionaryType.PAYMENT_STATUSES, Language.PL));
////        model.addAttribute("paymentMethods", dictionariesService.getDictionary(DictionaryType.ORDER_PAYMENT_METHODS, Language.PL));
////        model.addAttribute("shipmentTypes", dictionariesService.getDictionary(DictionaryType.SHIPMENT_TYPES, Language.PL));
////        model.addAttribute("drivers", dictionariesService.getDictionary(DictionaryType.DRIVERS, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//
//        model.addAttribute("defaultStartDate", LocalDate.now());
//        model.addAttribute("defaultEndDate", LocalDate.now().plusDays(6));
//
//        return "menu-preview";
//    }
//
//    @GetMapping({"/excel-menu-preview"})
//    public ResponseEntity<InputStreamResource> menuPreviewExcel(
//            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
//            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
//            @RequestParam(name = "diet_id") Long dietId,
//            @RequestParam(name = "kind_id") Long kindId) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.MENU_PREVIEW})) {
//            return null;
//        }
//        ExcelMenuPreviewData excel = null;
//        try {
//            long startTime = System.currentTimeMillis();
//            excel = menuService.generateMenuPreviewExcel(from, to, dietId, kindId);
//            long timeTaken = System.currentTimeMillis() - startTime;
//            log.info("Time Taken by {} is {}", "generateMenuPreviewExcel", timeTaken);
//        } catch (IOException e) {
//            log.error("Error during excel generation from: " + from + " to: " + to + " dietId: " + dietId + " kindId: " + kindId);
//        }
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + excel.getFileName())
//                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                .contentLength(excel.getSize()) //
//                .body(new InputStreamResource(excel.getData()));
//    }
//
//    @GetMapping({"/gallery"})
//    public String gallery(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.GALLERY})) {
//            return "denied";
//        }
//        useModelForGallery(model);
//        return "gallery";
//    }
//
//    private void useModelForGallery(Model model) {
//        model.addAttribute("paymentStatuses", dictionariesService.getDictionary(DictionaryType.PAYMENT_STATUSES, Language.PL));
//        model.addAttribute("paymentMethods", dictionariesService.getDictionary(DictionaryType.ORDER_PAYMENT_METHODS, Language.PL));
//        model.addAttribute("shipmentTypes", dictionariesService.getDictionary(DictionaryType.SHIPMENT_TYPES, Language.PL));
//        model.addAttribute("drivers", dictionariesService.getDictionary(DictionaryType.DRIVERS, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//
//        model.addAttribute("defaultStartDate", LocalDate.now());
//        model.addAttribute("defaultEndDate", LocalDate.now());
//    }
//
//    @GetMapping({"/gallery-image"})
//    public String galleryImage(Model model, @RequestParam(name = "id", required = false) Long id) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.GALLERY})) {
//            return "denied";
//        }
//        prepareModelForImage(model, id);
//        galleryService.clean();
//        return "gallery-image";
//    }
//
//    @GetMapping({"/customers-new"})
//    public String newCustomers(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.CUSTOMERS_STATS})) {
//            return "denied";
//        }
//        model.addAttribute("orderStatuses", dictionariesService.getDictionary(DictionaryType.ORDER_STATUSES, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//
//        model.addAttribute("defaultStartDate", LocalDate.now().minusMonths(1));
//        model.addAttribute("defaultEndDate", LocalDate.now());
//        return "customers-new";
//    }
//
//    @GetMapping({"/customer-groups"})
//    public String customerGroups(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.CUSTOMERS_GROUPS})) {
//            return "denied";
//        }
//
//        model.addAttribute("groupStatuses", dictionariesService.getDictionary(DictionaryType.CUSTOMER_GROUP_STATUSES, Language.PL));
//
//        return "customer-groups";
//    }
//
//    @GetMapping({"/customer"})
//    public String customer(@RequestParam(name = "id") Long customerId, Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.CUSTOMERS})) {
//            return "denied";
//        }
//        model.addAttribute("customerId", customerId);
//        model.addAttribute("customerGroups", dictionariesService.getDictionary(DictionaryType.CUSTOMER_GROUPS, Language.PL));
//        model.addAttribute("customerStatuses", dictionariesService.getDictionary(DictionaryType.CUSTOMER_STATUSES, Language.PL));
//        model.addAttribute("cities", dictionariesService.getDictionary(DictionaryType.CITIES, Language.PL));
//        model.addAttribute("yesNo", dictionariesService.getDictionary(DictionaryType.YES_NO, Language.PL));
//        model.addAttribute("customersLastOrderId", ordersService.getCustomersLastOrderId(customerId));
//        return "customer";
//    }
//
//
//
//    @PostMapping({"/upload"})
//    public String upload(Model model, @RequestParam(name = "id", required = false) Long id, @RequestParam("file") MultipartFile file) {
//        String error = null;
//        UploadResult result = null;
//        try {
//            result = galleryService.storeFile(id, file);
//        } catch (RuntimeException | IOException ex) {
//            error = ex.getMessage();
//        }
//        prepareModelForImage(model, id, error, result);
//        System.out.println(error);
//        return "gallery-image";
//    }
//
//    @GetMapping({"/customers"})
//    public String customers(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.CUSTOMERS})) {
//            return "denied";
//        }
//
//        model.addAttribute("customerTypes", dictionariesService.getDictionary(DictionaryType.CUSTOMER_TYPES, Language.PL));
//        model.addAttribute("customerGroups", dictionariesService.getDictionary(DictionaryType.CUSTOMER_GROUPS, Language.PL));
//        model.addAttribute("customerStatuses", dictionariesService.getDictionary(DictionaryType.CUSTOMER_STATUSES, Language.PL));
//
//        return "customers";
//    }
//
//    @GetMapping({"/allergens"})
//    public String allergens(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.DIETETICS_SETTINGS_ALLERGEN})) {
//            return "denied";
//        }
//        model.addAttribute("orderStatuses", dictionariesService.getDictionary(DictionaryType.ORDER_STATUSES, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//
//        model.addAttribute("defaultStartDate", LocalDate.now().minusMonths(1));
//        model.addAttribute("defaultEndDate", LocalDate.now());
//        return "allergens";
//    }
//
//    @GetMapping({"/products-types"})
//    public String productsTypes(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.DIETETICS_PRODUCT_TYPES})) {
//            return "denied";
//            // todo poprawne dane
//        }
//        model.addAttribute("orderStatuses", dictionariesService.getDictionary(DictionaryType.ORDER_STATUSES, Language.PL));
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//
//        model.addAttribute("defaultStartDate", LocalDate.now().minusMonths(1));
//        model.addAttribute("defaultEndDate", LocalDate.now());
//        return "products-types";
//    }
//
//    @GetMapping({"/admin/advices"})
//    public String advices() {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_ADVICES})) {
//            return "denied";
//        }
//        return "advices";
//    }
//
//    @GetMapping({"/admin/triggered-advices"})
//    public String triggeredAdvices() {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_TRIGGERED_ADVICES})) {
//            return "denied";
//        }
//
//        return "triggered-advices";
//    }
//
//    @GetMapping({"/admin/triggered-advice/{id}"})
//    public String triggeredAdvice(@PathVariable Long id, Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_TRIGGERED_ADVICES})) {
//            return "denied";
//        }
//        model.addAttribute("triggeredAdvice", new TriggeredAdviceGetResponse(adviserService.getTriggeredAdvice(id)));
//        return "triggered-advice";
//    }
//
//    @GetMapping({"/admin/advice/{id}"})
//    public String advice(@PathVariable Long id, Model model) {
//
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_ADVICES})) {
//            return "denied";
//        }
//
//        model.addAttribute("advice", new AdviceGetResponse(adviserService.getAdvice(id)));
//
//        return "advice";
//    }
//
//    @GetMapping({"/admin/accounts"})
//    public String accounts() {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_ACCOUNTS})) {
//            return "denied";
//        }
//
//        return "accounts";
//    }
//
//    @GetMapping({"/admin/account/{id}"})
//    public String account(@PathVariable Long id, Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_ACCOUNTS})) {
//            return "denied";
//        }
//
//        model.addAttribute("account", accountToResponses(accountsService.get(id)));
//
//        return "account";
//    }
//
//    @GetMapping({"/admin/applications"})
//    public String applications() {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_APPLICATIONS})) {
//            return "denied";
//        }
//
//        return "applications";
//    }
//
//    @GetMapping({"/admin/application/{id}"})
//    public String application(@PathVariable Long id, Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_APPLICATIONS})) {
//            return "denied";
//        }
//
//        model.addAttribute("app", applicationToResponse(applicationsService.get(id)));
//
//        return "application";
//    }
//
//    @GetMapping({"/admin/context-variables"})
//    public String contextVariables() {
//
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_CONTEXT_VARIABLES})) {
//            return "denied";
//        }
//
//        return "context-variables";
//    }
//
//    @GetMapping({"/admin/context-configs"})
//    public String contextConfigs() {
//
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_CONTEXT_CONFIGS})) {
//            return "denied";
//        }
//
//        return "context-configs";
//    }
//
//    @GetMapping({"/admin/context-config/{id}"})
//    public String contextConfig(@PathVariable Long id, Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_CONTEXT_CONFIGS})) {
//            return "denied";
//        }
//
//        model.addAttribute("contextConfig", contextConfigToResponse(contextConfigsService.get(id)));
//
//        return "context-config";
//    }
//
//    @GetMapping({"/admin/the-newest"})
//    public String newestAdvices(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.LIFE_ADVISER_COMMON})) {
//            return "denied";
//        }
//
//        return "the-newest";
//    }
//
//    @GetMapping({"/admin/likes"})
//    public String yourLikes(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.LIFE_ADVISER_COMMON})) {
//            return "denied";
//        }
//
//        return "likes";
//    }
//
//    @GetMapping({"/admin/not-liked"})
//    public String notLiked(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.LIFE_ADVISER_COMMON})) {
//            return "denied";
//        }
//
//        return "not-liked";
//    }
//
//    @GetMapping({"/admin/my-categories"})
//    public String myCategories(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.LIFE_ADVISER_COMMON})) {
//            return "denied";
//        }
//
//        return "my-categories";
//    }
//
//    @GetMapping({"/admin/market"})
//    public String market(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.LIFE_ADVISER_COMMON})) {
//            return "denied";
//        }
//
//        return "market";
//    }
//
//    @GetMapping({"/admin/advice-categories"})
//    public String categories(Model model) {
//        if (!authenticatedUser.hasAnyPermission(new Permissions[]{Permissions.ADVISER_ADVICE_CATEGORIES})) {
//            return "denied";
//        }
//
//        return "advice-categories";
//    }
//
//    @GetMapping({"/admin/suggestion"})
//    public String suggestion(Model model) {
//        if (!authenticatedUser.hasAnyPermission(Permissions.LIFE_ADVISER_COMMON)) {
//            return "denied";
//        }
//
//        return "suggestion";
//    }
//
//    @GetMapping({"/admin/options"})
//    public String options(Model model) {
//        if (!authenticatedUser.hasAnyPermission(Permissions.LIFE_ADVISER_COMMON)) {
//            return "denied";
//        }
//
//        return "options";
//    }
//
//    @GetMapping({"/kurs-debugowanie-java-w-intellij"})
//    public String courseDebugingJavaInIntellij(Model model) {
//        return "landing/landing-debugging-java";
//    }
//
//    @GetMapping({"/kurs-flutter-od-podstaw-aplikacje-mobilne"})
//    public String courseFlutter(Model model) {
//        return "landing/landing-flutter-basics";
//    }
//
//    @GetMapping({"/kurs-javascript-od-podstaw"})
//    public String courseJavascript(Model model) {
//        return "landing/landing-javascript-basics";
//    }
//
//    @GetMapping({"/kurs-html-od-podstaw"})
//    public String courseHtml(Model model) {
//        return "landing/landing-html-basics";
//    }
//
//    @GetMapping({"/kurs-javascript-dom"})
//    public String courseJavascriptDom(Model model) {
//        return "landing/landing-javascript-dom";
//    }
//
//    @GetMapping({"/kurs-sql-i-mysql"})
//    public String courseSql(Model model) {
//        return "landing/landing-mysql";
//    }
//
//    private ContextConfigGetResponse contextConfigToResponse(ContextConfigData data) {
//        return new ContextConfigGetResponse(data.getId(), data.getApplicationId(), data.getContext(), data.getName(), data.getType(), data.getValue());
//    }
//
//    private ApplicationGetResponse applicationToResponse(ApplicationData data) {
//        return new ApplicationGetResponse(data.getId(), data.getApplicationId(), data.getDescription(), data.getSecretKey());
//    }
//
//    private AccountGetResponse accountToResponses(AccountData data) {
//        return new AccountGetResponse(data.getId(), data.getName());
//    }
//
//    private void prepareModelForImage(Model model, Long id) {
//        prepareModelForImage(model, id, null, null);
//    }
//
//    private void prepareModelForImage(Model model, Long id, String error, UploadResult result) {
//        model.addAttribute("diets", dictionariesService.getDictionary(DictionaryType.DIETS, Language.PL));
//        model.addAttribute("id", id);
//        model.addAttribute("error", error);
//        model.addAttribute("uploadedFileName", result != null ? result.getFileName() : null);
//        model.addAttribute("imageKind", dictionariesService.getDictionary(DictionaryType.GALLERY_IMAGE_KIND, Language.PL));
//        model.addAttribute("data", new ImageData(""));
//        if (isModificationMode(id)) {
//            ImageData image = galleryService.getImage(id);
//            model.addAttribute("data", image);
//            if (result == null) {
//                model.addAttribute("uploadedFileName", image.getFileName());
//            }
//        }
//    }
//
//    private boolean isModificationMode(Long id) {
//        return id != null;
//    }
//
//    private long initPageWhenNotSet(@RequestParam(name = "page", required = false) Long page, int i) {
//        return page != null ? page : i;
//    }
}
