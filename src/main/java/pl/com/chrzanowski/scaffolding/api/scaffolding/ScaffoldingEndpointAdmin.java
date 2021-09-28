package pl.com.chrzanowski.scaffolding.api.scaffolding;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.courseplatform.ChangePasswordRequest;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.*;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.*;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationsFromPanelService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdmin {

    private ScaffUsersService scaffUsersService;
    private ScaffNotificationsFromPanelService notificationsFromPanelService;
    private ScaffStatisticService scaffStatisticService;
    private ScaffVehiclesService scaffVehiclesService;
    private ScaffFuelTypeService fuelTypeService;
    private ScaffServiceActionsService serviceActionsService;
    private ScaffServiceWorkshopsService workshopsService;

    public ScaffoldingEndpointAdmin(ScaffUsersService scaffUsersService,
                                    ScaffNotificationsFromPanelService notificationsFromPanelService,
                                    ScaffStatisticService scaffStatisticService,
                                    ScaffVehiclesService scaffVehiclesService,
                                    ScaffFuelTypeService fuelTypeService,
                                    ScaffServiceActionsService serviceActionsService,
                                    ScaffServiceWorkshopsService workshopsService) {
        this.scaffUsersService = scaffUsersService;
        this.notificationsFromPanelService = notificationsFromPanelService;
        this.scaffStatisticService = scaffStatisticService;
        this.scaffVehiclesService = scaffVehiclesService;
        this.fuelTypeService = fuelTypeService;
        this.serviceActionsService = serviceActionsService;
        this.workshopsService = workshopsService;
    }

    @GetMapping("/users")
    public List<ScaffUserGetResponse> getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "login", required = false) String loginLike
    ) {
        List<ScaffUserData> users = scaffUsersService.find(new ScaffUsersFilter(loginLike, page, pageSize
        ));
        return usersToResponses(users);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody ScaffUserPostRequest request, HttpServletRequest httpServletRequest) {
        scaffUsersService.registerUser(
                new ScaffUserData(
                        request.getLogin(),
                        request.getPasswordHash(),
                        request.getLanguage(),
                        request.getRegulationAccepted(),
                        request.getNewsletterAccepted(),
                        request.getIsEnabled(),
                        request.getIsEmailConfirmed(),
                        request.getAuthorities(),
                        httpServletRequest));
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody ScaffUserPutRequest request) {
        ScaffUserData data = scaffUsersService.get(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        scaffUsersService.update(new ScaffUserData(id,
                request.getLogin(),
                data.getPasswordHash(),
                request.getLanguage(),
                request.getRegulationAccepted(),
                request.getNewsletterAccepted(),
                request.getIsEnabled(),
                request.getIsEmailConfirmed(),
                LocalDateTime.parse(request.getRegistrationDatetime(), formatter),
                request.getAuthorities()));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        scaffUsersService.delete(id);
    }

    @PutMapping("/user-change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        scaffUsersService.changePasswordAdmin(id, request.getNewPasswordHash());
    }

    @PostMapping("/notifications")
    public void createNotifications(@RequestBody ScaffNotificationsPostRequest request) {
        notificationsFromPanelService.createNotifications(new ScaffCreateNotificationsParameters(request));
    }

    @GetMapping(path = "/vehicles", produces = "application/json; charset=UTF-8")
    public ScaffVehiclesRequestGetResponse vehicles(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        List<ScaffVehicleData> vehicles = scaffVehiclesService.find(new ScaffVehicleFilter(page, pageSize));
        return new ScaffVehiclesRequestGetResponse(vehiclesToResponse(vehicles));
    }

    @GetMapping(path = "/vehicle/{id}", produces = "application/json; charset=UTF-8")
    public ScaffVehicleRequestGetResponse vehicleById(
            @PathVariable Long id) throws SQLException {
        ScaffVehicleData vehicle = scaffVehiclesService.findById(new ScaffVehicleFilter(id));
        return new ScaffVehicleRequestGetResponse(vehicleToResponse(vehicle));
    }

    @PostMapping(path = "/vehicle", consumes = "application/json; charset=UTF-8")
    public void addVehicle(@RequestBody ScaffVehiclePostRequest request) {
        scaffVehiclesService.add(new ScaffVehicleData(
                request.getBrandName(),
                request.getModelName(),
                request.getRegistrationNumber(),
                request.getVin(),
                request.getProductionYear(),
                request.getFirstRegistrationDate(),
                request.getFreePlacesForTechnicalInspections(),
                request.getFuelTypeId(),
                request.getVehicleTypeId(),
                request.getLength(),
                request.getWidth(),
                request.getHeight()));
    }

    @PutMapping(path = "/vehicle/{id}")
    public void updateVehicle(@PathVariable Long id, @RequestBody ScaffVehiclePutRequest request) throws SQLException {
        scaffVehiclesService.update(new ScaffVehicleData(
                id,
                request.getBrandName(),
                request.getModelName(),
                request.getRegistrationNumber(),
                request.getVin(),
                request.getProductionYear(),
                request.getFirstRegistrationDate(),
                request.getFreePlacesForTechnicalInspections(),
                request.getFuelTypeId(),
                request.getVehicleTypeId(),
                request.getLength(),
                request.getWidth(),
                request.getHeight())
        );
    }

    @GetMapping(path = "/fuel-types", produces = "application/json; charset=UTF-8")
    public ScaffFuelTypeRequestGetResponse fuelTypes(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                     @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ScaffFuelTypeData> fuelTypes = fuelTypeService.find(new ScaffFuelTypeFilter(page, pageSize));
        return new ScaffFuelTypeRequestGetResponse(fuelTypeToResponse(fuelTypes));
    }

    @GetMapping(path = "/vehicle-service-actions/{id}", produces = "application/json; charset=UTF-8")
    public ScaffServiceActionsRequestGetResponse vehicleServiceActions(
            @PathVariable Long id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ScaffServiceActionsData> actions = serviceActionsService.find(new ScaffServiceActionsFilter(id, page,
                pageSize));
        return new ScaffServiceActionsRequestGetResponse(actionsToResponse(actions));
    }

    @GetMapping(path = "/vehicle-service-action/{id}", produces = "application/json; charset=UTF-8")
    public ScaffServiceActionRequestGetResponse vehicleServiceActionById(
            @PathVariable Long id) {
        ScaffServiceActionsData serviceAction = serviceActionsService.findById(new ScaffServiceActionsFilter(id));
        return new ScaffServiceActionRequestGetResponse(actionToResponse(serviceAction));
    }

    @PostMapping(path = "/vehicle-service-action", consumes = "application/json; charset=UTF-8")
    public void addVehicleServiceAction(@RequestBody ScaffServiceActionPostRequest request) {
        serviceActionsService.add(new ScaffServiceActionsData(
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription()));
    }

    @PutMapping(path = "/vehicle-service-action/{id}")
    public void updateServiceAction(@PathVariable Long id, @RequestBody ScaffServiceActionPutRequest request) {
        serviceActionsService.update(new ScaffServiceActionsData(
                request.getId(),
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription()
        ));
    }

    @GetMapping(path = "/workshops", produces = "application/json; charset=UTF-8")
    public ScaffServiceWorkshopsRequestGetResponse workshops(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        List<ScaffServiceWorkshopsData> workshops = workshopsService.find(new ScaffServiceWorkshopsFilter(page, pageSize));
        return new ScaffServiceWorkshopsRequestGetResponse(workshopsToResponse(workshops));
    }

    @GetMapping(path = "/workshop/{id}", produces = "application/json; charset=UTF-8")
    public ScaffServiceWorkshopRequestGetResponse workshopById(
            @PathVariable Long id) {
        ScaffServiceWorkshopsData workshop = workshopsService.find(new ScaffServiceWorkshopsFilter(id)).get(0);
        return new ScaffServiceWorkshopRequestGetResponse(workshopToResponse(workshop));
    }

    @PostMapping(path = "/workshop", consumes = "application/json; charset=UTF-8")
    public void addWorkshop(@RequestBody ScaffServiceWorkshopPostRequest request) {
        workshopsService.add(new ScaffServiceWorkshopsData(
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity()));
    }


    @PutMapping(path = "/workshop/{id}", consumes = "application/json; charset=UTF-8")
    public void updateWorkshop(@PathVariable Long id, @RequestBody ScaffServiceWorkshopPutRequest request) {
        workshopsService.update(new ScaffServiceWorkshopsData(
                id,
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity()));
    }

    private List<ScaffVehicleGetResponse> vehiclesToResponse(List<ScaffVehicleData> vehicles) {
        List<ScaffVehicleGetResponse> list = new ArrayList<>();
        for (ScaffVehicleData vehicle : vehicles) {
            list.add(new ScaffVehicleGetResponse(
                    vehicle.getId(),
                    vehicle.getBrandName(),
                    vehicle.getModelName(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getVin(),
                    vehicle.getProductionYear(),
                    vehicle.getFirstRegistrationDate(),
                    vehicle.getFreePlacesForTechnicalInspections(),
                    vehicle.getFuelType(),
                    vehicle.getVehicleType(),
                    vehicle.getLength(),
                    vehicle.getWidth(),
                    vehicle.getHeight())
            );
        }
        return list;
    }

    private ScaffVehicleGetResponse vehicleToResponse(ScaffVehicleData vehicleData) {
        return new ScaffVehicleGetResponse(
                vehicleData.getId(),
                vehicleData.getBrandName(),
                vehicleData.getModelName(),
                vehicleData.getRegistrationNumber(),
                vehicleData.getVin(),
                vehicleData.getProductionYear(),
                vehicleData.getFirstRegistrationDate(),
                vehicleData.getFreePlacesForTechnicalInspections(),
                vehicleData.getFuelType(),
                vehicleData.getVehicleType(),
                vehicleData.getLength(),
                vehicleData.getWidth(),
                vehicleData.getHeight());
    }

    private List<ScaffUserGetResponse> usersToResponses(List<ScaffUserData> users) {
        List<ScaffUserGetResponse> list = new ArrayList<>();
        for (ScaffUserData user : users) {
            list.add(new ScaffUserGetResponse(user));
        }
        return list;
    }

    private List<ScaffFuelTypeGetResponse> fuelTypeToResponse(List<ScaffFuelTypeData> fuelTypeData) {
        List<ScaffFuelTypeGetResponse> list = new ArrayList<>();
        for (ScaffFuelTypeData type : fuelTypeData) {
            list.add(new ScaffFuelTypeGetResponse(type));
        }
        return list;
    }

    private List<ScaffServiceActionGetResponse> actionsToResponse(List<ScaffServiceActionsData> actions) {
        List<ScaffServiceActionGetResponse> list = new ArrayList<>();
        for (ScaffServiceActionsData data : actions) {
            list.add(new ScaffServiceActionGetResponse(data));
        }
        return list;
    }

    private ScaffServiceActionGetResponse actionToResponse(ScaffServiceActionsData data) {
        return new ScaffServiceActionGetResponse(data);
    }

    private List<ScaffServiceWorkshopsGetResponse> workshopsToResponse(List<ScaffServiceWorkshopsData> workshops) {
        List<ScaffServiceWorkshopsGetResponse> list = new ArrayList<>();
        for (ScaffServiceWorkshopsData workshop : workshops) {
            list.add(new ScaffServiceWorkshopsGetResponse(
                    workshop.getId(),
                    workshop.getName(),
                    workshop.getTaxNumber(),
                    workshop.getStreet(),
                    workshop.getBuildingNo(),
                    workshop.getApartmentNo(),
                    workshop.getPostalCode(),
                    workshop.getCity())
            );
        }
        return list;
    }

    private ScaffServiceWorkshopsGetResponse workshopToResponse(ScaffServiceWorkshopsData workshop) {
        return new ScaffServiceWorkshopsGetResponse(
                workshop.getId(),
                workshop.getName(),
                workshop.getTaxNumber(),
                workshop.getStreet(),
                workshop.getBuildingNo(),
                workshop.getApartmentNo(),
                workshop.getPostalCode(),
                workshop.getCity()
        );
    }
}
