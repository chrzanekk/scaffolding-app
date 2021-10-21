package pl.com.chrzanowski.scaffolding.api;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.domain.*;
import pl.com.chrzanowski.scaffolding.logic.*;
import pl.com.chrzanowski.scaffolding.logic.notifications.NotificationsFromPanelService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdmin {

    private UserService userService;
    private NotificationsFromPanelService notificationsFromPanelService;
    private IVehicles vehicles;
    private IFuelTypes fuelTypes;
    private IServiceActions serviceActions;
    private ServiceWorkshopsService workshopsService;
    private IServiceActonTypes serviceActonTypes;
    private MarketingService marketingService;
    private ITireSeasons tireSeason;
    private IVehicleModels vehicleModels;
    private IVehicleBrands vehicleBrands;
    private VehiclesBrandsAndModelsService vehiclesBrandsAndModelsService;
    private IVehicleTires vehicleTires;

    public ScaffoldingEndpointAdmin(UserService userService,
                                    NotificationsFromPanelService notificationsFromPanelService,
                                    IVehicles vehicles,
                                    IFuelTypes fuelTypes,
                                    IServiceActions serviceActions,
                                    ServiceWorkshopsService workshopsService,
                                    IServiceActonTypes serviceActonTypes,
                                    MarketingService marketingService,
                                    ITireSeasons tireSeason,
                                    IVehicleModels vehicleModels,
                                    IVehicleBrands vehicleBrands,
                                    VehiclesBrandsAndModelsService vehiclesBrandsAndModelsService,
                                    IVehicleTires vehicleTires) {
        this.userService = userService;
        this.notificationsFromPanelService = notificationsFromPanelService;
        this.vehicles = vehicles;
        this.fuelTypes = fuelTypes;
        this.serviceActions = serviceActions;
        this.workshopsService = workshopsService;
        this.serviceActonTypes = serviceActonTypes;
        this.marketingService = marketingService;
        this.tireSeason = tireSeason;
        this.vehiclesBrandsAndModelsService = vehiclesBrandsAndModelsService;
        this.vehicleModels = vehicleModels;
        this.vehicleBrands = vehicleBrands;
        this.vehicleTires = vehicleTires;
    }

    @GetMapping("/users")
    public List<UserGetResponse> getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "login", required = false) String loginLike
    ) {
        List<UserData> users = userService.find(new UsersFilter(loginLike, page, pageSize
        ));
        return usersToResponses(users);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserPostRequest request, HttpServletRequest httpServletRequest) {
        userService.registerUser(
                new UserData(
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
    public void updateUser(@PathVariable Long id, @RequestBody UserPutRequest request) {
        UserData data = userService.get(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        userService.update(new UserData(id,
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
        userService.delete(id);
    }

    @PutMapping("/user-change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        userService.changePasswordAdmin(id, request.getNewPasswordHash());
    }

    @PostMapping("/notifications")
    public void createNotifications(@RequestBody NotificationsPostRequest request) {
        notificationsFromPanelService.createNotifications(new CreateNotificationsParameters(request));
    }

    @PostMapping("/marketing/newsletter")
    public void newsletter(@RequestBody NewsletterData request) {
        marketingService.sendNewsletter(request);
    }

    @GetMapping(path = "/vehicles", produces = "application/json; charset=UTF-8")
    public VehiclesRequestGetResponse vehicles(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        List<VehicleData> vehicles = this.vehicles.find(new VehicleFilter(page, pageSize));
        return new VehiclesRequestGetResponse(vehiclesToResponse(vehicles));
    }

    @GetMapping(path = "/vehicle/{id}", produces = "application/json; charset=UTF-8")
    public VehicleRequestGetResponse vehicleById(
            @PathVariable Long id) {
        VehicleData vehicle = vehicles.findById(new VehicleFilter(id));
        return new VehicleRequestGetResponse(vehicleToResponse(vehicle));
    }

    @PostMapping(path = "/vehicle", consumes = "application/json; charset=UTF-8")
    public void addVehicle(@RequestBody VehiclePostRequest request) {
        vehicles.add(new VehicleData(
                request.getBrandId(),
                request.getModelId(),
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
    public void updateVehicle(@PathVariable Long id, @RequestBody VehiclePutRequest request) {
        vehicles.update(new VehicleData(
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

    @GetMapping(path = "/brands", produces = "application/json; charset=UTF-8")
    public VehicleBrandsRequestGetResponse brands(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<VehicleBrandData> brands =
                vehicleBrands.find(new VehicleBrandFilter());
        return new VehicleBrandsRequestGetResponse(brandsToResponse(brands));
    }

    @GetMapping(path = "/brands/{id}", produces = "application/json; charset=UTF-8")
    public VehicleBrandRequestGetResponse brandById(
            @PathVariable Long id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        VehicleBrandData brand = vehicleBrands.find(new VehicleBrandFilter(id)).get(0);
        return new VehicleBrandRequestGetResponse(brandToResponse(brand));
    }

    @PostMapping(path = "/brands", consumes = "application/json; charset=UTF-8")
    public void addBrand(@RequestBody VehiclesBrandsPostRequest request) {
        vehicleBrands.add(new VehicleBrandData(
                request.getBrandName()
        ));
    }

    @PutMapping(path = "/brands/{id}")
    public void updateBrand(@PathVariable Long id, @RequestBody VehiclesBrandsPutRequest request) {
        vehicleBrands.update(new VehicleBrandData(
                id,
                request.getBrandName(),
                LocalDateTime.now()
        ));
    }

    @GetMapping(path = "/brands/{id}/models", produces = "application/json; charset=UTF-8")
    public VehiclesModelRequestGetResponse modelsByBrandId(@PathVariable Long id) {
        List<VehicleModelData> models = vehicleModels.find(new VehicleModelFilter(null, id));
        return new VehiclesModelRequestGetResponse(modelsToResponse(models));
    }



    @GetMapping(path = "/tires/{id}", produces = "application/json; charset=UTF-8")
    public VehicleTiresRequestGetResponse tiresByVehicleId(@PathVariable Long id,
                                                           @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                           @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<VehicleTiresData> tires = vehicleTires.find(new VehicleTiresFilter(id, page, pageSize));
        return new VehicleTiresRequestGetResponse(tiresToResponse(tires));
    }

    @GetMapping(path = "/fuel-types", produces = "application/json; charset=UTF-8")
    public FuelTypeRequestGetResponse fuelTypes(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<FuelTypeData> fuelTypes = this.fuelTypes.find(new FuelTypeFilter(page, pageSize));
        return new FuelTypeRequestGetResponse(fuelTypeToResponse(fuelTypes));
    }

    @GetMapping(path = "/vehicle-service-actions/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionsRequestGetResponse vehicleServiceActions(
            @PathVariable Long id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionsData> actions = serviceActions.find(new ServiceActionsFilter(id, page,
                pageSize));
        return new ServiceActionsRequestGetResponse(actionsToResponse(actions));
    }

    @GetMapping(path = "/vehicle-service-action/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionRequestGetResponse vehicleServiceActionById(
            @PathVariable Long id) {
        ServiceActionsData serviceAction = serviceActions.findById(new ServiceActionsFilter(id));
        return new ServiceActionRequestGetResponse(actionToResponse(serviceAction));
    }

    @PostMapping(path = "/vehicle-service-action", consumes = "application/json; charset=UTF-8")
    public void addVehicleServiceAction(@RequestBody ServiceActionPostRequest request) {
        serviceActions.add(new ServiceActionsData(
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription()));
    }

    @PutMapping(path = "/vehicle-service-action/{id}")
    public void updateServiceAction(@PathVariable Long id, @RequestBody ServiceActionPutRequest request) {
        serviceActions.update(new ServiceActionsData(
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
    public ServiceWorkshopsRequestGetResponse workshops(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        List<ServiceWorkshopsData> workshops = workshopsService.find(new ServiceWorkshopsFilter(page, pageSize));
        return new ServiceWorkshopsRequestGetResponse(workshopsToResponse(workshops));
    }

    @GetMapping(path = "/workshop/{id}", produces = "application/json; charset=UTF-8")
    public ServiceWorkshopRequestGetResponse workshopById(
            @PathVariable Long id) {
        ServiceWorkshopsData workshop = workshopsService.find(new ServiceWorkshopsFilter(id)).get(0);
        return new ServiceWorkshopRequestGetResponse(workshopToResponse(workshop));
    }

    @PostMapping(path = "/workshop", consumes = "application/json; charset=UTF-8")
    public void addWorkshop(@RequestBody ServiceWorkshopPostRequest request) {
        workshopsService.add(new ServiceWorkshopsData(
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity()));
    }


    @PutMapping(path = "/workshop/{id}", consumes = "application/json; charset=UTF-8")
    public void updateWorkshop(@PathVariable Long id, @RequestBody ServiceWorkshopPutRequest request) {
        workshopsService.update(new ServiceWorkshopsData(
                id,
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity()));
    }


    @GetMapping(path = "/service-action-types", produces = "application/json; charset=UTF-8")
    public ServiceActionTypesRequestGetResponse serviceActionTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionTypeData> serviceActionTypes =
                serviceActonTypes.find(new ServiceActionTypesFilter(page, pageSize));
        return new ServiceActionTypesRequestGetResponse(actionTypesToResponse(serviceActionTypes));
    }

    @GetMapping(path = "/service-action-type/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionTypeRequestGetResponse serviceActionTypesById(
            @PathVariable Long id) {
        ServiceActionTypeData serviceActionType = serviceActonTypes.find(new ServiceActionTypesFilter(id)).get(0);
        return new ServiceActionTypeRequestGetResponse(actionTypeToResponse(serviceActionType));
    }

    @PostMapping(path = "/service-action-type", consumes = "application/json; charset=UTF-8")
    public void addServiceActionType(@RequestBody ServiceActionTypesPostRequest request) {
        serviceActonTypes.add(new ServiceActionTypeData(request.getName()));
    }

    @PutMapping(path = "/service-action-type/{id}", consumes = "application/json; charset=UTF-8")
    public void updateServiceActionType(@PathVariable Long id,
                                        @RequestBody ServiceActionTypesPutRequest request) {
        serviceActonTypes.update(new ServiceActionTypeData(id, request.getName()));
    }

    @GetMapping(path = "/tire-seasons", produces = "application/json; charset=UTF-8")
    public TireSeasonsRequestGetResponse tireSeasons(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                     @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<TireSeasonData> tireSeasons = tireSeason.find(new TireSeasonFilter(page, pageSize));
        return new TireSeasonsRequestGetResponse(tireSeasonsToResponse(tireSeasons));

    }

    @PostMapping(path = "/tire-season", consumes = "application/json; charset=UTF-8")
    public void addTireSeason(@RequestBody TireSeasonPostRequest request) {
        tireSeason.add(new TireSeasonData(request.getName()));
    }

    @PutMapping(path = "/tire-season/{id}", consumes = "application/json; charset=UTF-8")
    public void updateTireSeason(@PathVariable Long id,
                                 @RequestBody TireSeasonPutRequest request) {
        tireSeason.update(new TireSeasonData(id, request.getName()));
    }

    private List<VehicleGetResponse> vehiclesToResponse(List<VehicleData> vehicles) {
        List<VehicleGetResponse> list = new ArrayList<>();
        for (VehicleData vehicle : vehicles) {
            list.add(new VehicleGetResponse(
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

    private VehicleGetResponse vehicleToResponse(VehicleData vehicleData) {
        return new VehicleGetResponse(
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

    private List<UserGetResponse> usersToResponses(List<UserData> users) {
        List<UserGetResponse> list = new ArrayList<>();
        for (UserData user : users) {
            list.add(new UserGetResponse(user));
        }
        return list;
    }

    private List<FuelTypeGetResponse> fuelTypeToResponse(List<FuelTypeData> fuelTypeData) {
        List<FuelTypeGetResponse> list = new ArrayList<>();
        for (FuelTypeData type : fuelTypeData) {
            list.add(new FuelTypeGetResponse(type));
        }
        return list;
    }

    private List<ServiceActionGetResponse> actionsToResponse(List<ServiceActionsData> actions) {
        List<ServiceActionGetResponse> list = new ArrayList<>();
        for (ServiceActionsData data : actions) {
            list.add(new ServiceActionGetResponse(data));
        }
        return list;
    }

    private ServiceActionGetResponse actionToResponse(ServiceActionsData data) {
        return new ServiceActionGetResponse(data);
    }

    private List<ServiceWorkshopsGetResponse> workshopsToResponse(List<ServiceWorkshopsData> workshops) {
        List<ServiceWorkshopsGetResponse> list = new ArrayList<>();
        for (ServiceWorkshopsData workshop : workshops) {
            list.add(new ServiceWorkshopsGetResponse(
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

    private ServiceWorkshopsGetResponse workshopToResponse(ServiceWorkshopsData workshop) {
        return new ServiceWorkshopsGetResponse(
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

    private List<ServiceActionTypesGetResponse> actionTypesToResponse(List<ServiceActionTypeData> actionTypes) {
        List<ServiceActionTypesGetResponse> list = new ArrayList<>();
        for (ServiceActionTypeData data : actionTypes) {
            list.add(new ServiceActionTypesGetResponse(
                    data.getId(),
                    data.getName()
            ));
        }
        return list;
    }

    private ServiceActionTypesGetResponse actionTypeToResponse(ServiceActionTypeData actionType) {
        return new ServiceActionTypesGetResponse(actionType.getId(), actionType.getName());
    }

    private List<TireSeasonGetResponse> tireSeasonsToResponse(List<TireSeasonData> tireSeasons) {
        List<TireSeasonGetResponse> list = new ArrayList<>();
        for (TireSeasonData data : tireSeasons) {
            list.add(new TireSeasonGetResponse(
                    data.getId(),
                    data.getName()
            ));
        }
        return list;
    }

    private List<VehicleModelGetResponse> modelsToResponse(List<VehicleModelData> models) {
        List<VehicleModelGetResponse> list = new ArrayList<>();
        for (VehicleModelData data : models) {
            list.add(new VehicleModelGetResponse(
                    data.getId(),
                    data.getBrandId(),
                    data.getName()
            ));
        }
        return list;
    }

    private List<VehicleBrandGetResponse> brandsToResponse(List<VehicleBrandData> brands) {
        List<VehicleBrandGetResponse> list = new ArrayList<>();
        for (VehicleBrandData data : brands) {
            list.add(new VehicleBrandGetResponse(
                    data.getId(),
                    data.getName()
            ));
        }
        return list;
    }

    private List<VehicleTiresGetResponse> tiresToResponse(List<VehicleTiresData> tires) {
        List<VehicleTiresGetResponse> list = new ArrayList<>();
        for (VehicleTiresData data : tires) {
            list.add(new VehicleTiresGetResponse(
                    data.getId(),
                    data.getVehicleId(),
                    data.getTireId(),
                    data.getStatus(),
                    data.getProductionYear(),
                    data.getPurchaseDate(),
                    data.getBrand(),
                    data.getModel(),
                    data.getWidth(),
                    data.getProfile(),
                    data.getDiameter(),
                    data.getSpeedIndex(),
                    data.getLoadIndex(),
                    data.getReinforced(),
                    data.isRunOnFlat(),
                    data.getSeasonId(),
                    data.getSeasonName()
            ));
        }
        return list;
    }

    private VehicleBrandGetResponse brandToResponse(VehicleBrandData brand) {
        return new VehicleBrandGetResponse(brand.getId(), brand.getName());
    }
}