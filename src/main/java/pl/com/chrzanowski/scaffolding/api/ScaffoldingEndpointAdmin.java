package pl.com.chrzanowski.scaffolding.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.domain.*;
import pl.com.chrzanowski.scaffolding.logic.*;
import pl.com.chrzanowski.scaffolding.logic.notifications.NotificationsFromPanelService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private WorkshopsService workshopsService;
    private IServiceActonTypes serviceActonTypes;
    private MarketingService marketingService;
    private ITireSeasons tireSeason;
    private IVehicleModels vehicleModels;
    private IVehicleBrands vehicleBrands;
    private IVehicleTires vehicleTires;
    private WorkshopServiceTypeService workshopServiceTypeService;
    private DictionariesService dictionariesService;

    public ScaffoldingEndpointAdmin(UserService userService,
                                    NotificationsFromPanelService notificationsFromPanelService,
                                    IVehicles vehicles,
                                    IFuelTypes fuelTypes,
                                    IServiceActions serviceActions,
                                    WorkshopsService workshopsService,
                                    IServiceActonTypes serviceActonTypes,
                                    MarketingService marketingService,
                                    ITireSeasons tireSeason,
                                    IVehicleModels vehicleModels,
                                    IVehicleBrands vehicleBrands,
                                    IVehicleTires vehicleTires,
                                    WorkshopServiceTypeService workshopServiceTypeService,
                                    DictionariesService dictionariesService) {
        this.userService = userService;
        this.notificationsFromPanelService = notificationsFromPanelService;
        this.vehicles = vehicles;
        this.fuelTypes = fuelTypes;
        this.serviceActions = serviceActions;
        this.workshopsService = workshopsService;
        this.serviceActonTypes = serviceActonTypes;
        this.marketingService = marketingService;
        this.tireSeason = tireSeason;
        this.vehicleModels = vehicleModels;
        this.vehicleBrands = vehicleBrands;
        this.vehicleTires = vehicleTires;
        this.workshopServiceTypeService = workshopServiceTypeService;
        this.dictionariesService = dictionariesService;
    }

    @GetMapping("/users")
    public UsersRequestGetResponse getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "login", required = false) String loginLike
    ) {
        List<UserData> users = userService.find(new UsersFilter(loginLike, page, pageSize
        ));
        return new UsersRequestGetResponse(usersToResponses(users));
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
        userService.update(new UserData(id,
                request.getFirstName(),
                request.getSecondName(),
                request.getLogin(),
                data.getPasswordHash(),
                request.getLanguage(),
                request.getRegulationAccepted(),
                request.getNewsletterAccepted(),
                request.getIsEnabled(),
                request.getIsEmailConfirmed(),
                request.getRegistrationDatetime(),
                request.getAuthorities(),
                data.getRegistrationIp(),
                data.getRegistrationUserAgent()));
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
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "brand_id", required = false) Long brandId,
            @RequestParam(name = "model_id", required = false) Long modelId,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber) throws SQLException {
        List<VehicleData> vehiclesList = vehicles.find(new VehicleFilter(
                registrationNumber,
                brandId,
                modelId,
                page,
                pageSize));
        return new VehiclesRequestGetResponse(vehiclesToResponse(vehiclesList));
    }

    @GetMapping(path = "/vehicles-without-tires", produces = "application/json; charset=UTF-8")
    public VehiclesRequestGetResponse vehiclesWithoutTires() {
        List<VehicleData> vehicleList = vehicles.findWithoutTires(new VehicleFilter());
        return new VehiclesRequestGetResponse(vehiclesWithoutTiresToResponse(vehicleList));
    }

    @GetMapping(path = "/vehicle/{id}", produces = "application/json; charset=UTF-8")
    public VehicleRequestGetResponse vehicleById(
            @PathVariable Long id) {
        VehicleData vehicle = vehicles.findById(new VehicleFilter(id));
        return new VehicleRequestGetResponse(vehicleToResponse(vehicle));
    }

    @GetMapping(path = "/vehicle-tires-check/{id}", produces = "application/json; charset=UTF-8")
    public VehicleRequestGetResponse vehicleTiresCheck(
            @PathVariable Long id) {
        VehicleData vehicle = vehicles.findByIdAndCheckTires(new VehicleFilter(id));
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
            @RequestParam(name = "brand_name", required = false) String brandName) {
        List<VehicleBrandData> brands = vehicleBrands.find(new VehicleBrandFilter(brandName));
        return new VehicleBrandsRequestGetResponse(brandsToResponse(brands));
    }

    @GetMapping(path = "/brands/{id}", produces = "application/json; charset=UTF-8")
    public VehicleBrandRequestGetResponse brandById(
            @PathVariable Long id) {
        VehicleBrandData brand = vehicleBrands.find(new VehicleBrandFilter(id)).get(0);
        return new VehicleBrandRequestGetResponse(brandToResponse(brand));
    }

    @PostMapping(path = "/brands", consumes = "application/json; charset=UTF-8")
    public void addBrand(@RequestBody VehicleBrandsPostRequest request) {
        vehicleBrands.add(new VehicleBrandData(
                request.getBrandName()
        ));
    }

    @PutMapping(path = "/brands/{id}", consumes = "application/json; charset=UTF-8")
    public void updateBrand(@PathVariable Long id, @RequestBody VehicleBrandsPutRequest request) {
        vehicleBrands.update(new VehicleBrandData(
                id,
                request.getBrandName(),
                LocalDateTime.now()
        ));
    }

    @GetMapping(path = "/brands/{id}/models", produces = "application/json; charset=UTF-8")
    public VehicleModelsRequestGetResponse modelsByBrandId(@PathVariable Long id) {
        List<VehicleModelData> models = vehicleModels.find(new VehicleModelFilter(null, id));
        return new VehicleModelsRequestGetResponse(modelsToResponse(models));
    }

    @GetMapping(path = "/models", produces = "application/json; charset=UTF-8")
    public VehicleModelsRequestGetResponse allModels() {
        List<VehicleModelData> models = vehicleModels.find(new VehicleModelFilter());
        return new VehicleModelsRequestGetResponse(modelsToResponse(models));
    }

    @GetMapping(path = "/brands/{brandId}/models/{modelId}", produces = "application/json; charset=UTF-8")
    public VehicleModelRequestGetResponse modelById(@PathVariable Long brandId,
                                                    @PathVariable Long modelId) {
        VehicleModelData model = vehicleModels.find(new VehicleModelFilter(modelId, brandId)).get(0);
        return new VehicleModelRequestGetResponse(modelToResponse(model));
    }

    @PostMapping(path = "/brands/{id}/models", consumes = "application/json; charset=UTF-8")
    public void addModel(@PathVariable Long id, @RequestBody VehicleModelsPostRequest request) {
        vehicleModels.add(new VehicleModelData(
                request.getModelName(),
                id
        ));
    }

    @PutMapping(path = "/brands/{brandId}/models/{modelId}", consumes = "application/json; charset=UTF-8")
    public void updateModel(@PathVariable Long brandId,
                            @PathVariable Long modelId,
                            @RequestBody VehicleModelsPutRequest request) {
        vehicleModels.update(new VehicleModelData(
                modelId,
                brandId,
                request.getModelName(),
                LocalDateTime.now()
        ));
    }

    @GetMapping(path = "/vehicles/{id}/tires", produces = "application/json; charset=UTF-8")
    public VehicleTiresRequestGetResponse tiresByVehicleId(@PathVariable Long id,
                                                           @RequestParam(name = "status", required = false) String status,
                                                           @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                           @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<VehicleTiresData> tires = vehicleTires.find(new VehicleTiresFilter(id, status, page, pageSize));
        return new VehicleTiresRequestGetResponse(tiresToResponse(tires));
    }

    @GetMapping(path = "/vehicles/{vehicleId}/tires/{tireId}", produces = "application/json; charset=UTF-8")
    public VehicleTireRequestGetResponse tireById(@PathVariable Long vehicleId,
                                                  @PathVariable Long tireId) {
        VehicleTiresData tire = vehicleTires.getTire(new VehicleTiresFilter(tireId, vehicleId));
        return new VehicleTireRequestGetResponse(tireToResponse(tire));
    }

    @PostMapping(path = "/vehicles/{id}/tires", consumes = "application/json; charset=UTF-8")
    public void addTires(
            @PathVariable Long id,
            @RequestBody VehicleTiresPostRequest request) {
        vehicleTires.create(new VehicleTiresData(
                id,
                request.getBrand(),
                request.getModel(),
                request.getProductionYear(),
                request.getPurchaseDate(),
                request.getWidth(),
                request.getProfile(),
                request.getDiameter(),
                request.getType(),
                request.getSpeedIndex(),
                request.getCapacityIndex(),
                request.getReinforced(),
                request.getRunOnFlat(),
                request.getSeasonId(),
                request.getStatus()
        ));
    }

    @PutMapping(path = "/vehicles/{vehicleId}/tires/{tireId}", consumes = "application/json; charset=UTF-8")
    public void updateTires(@PathVariable Long tireId,
                            @PathVariable Long vehicleId,
                            @RequestBody VehicleTiresPutRequest request) {
        vehicleTires.update(new VehicleTiresData(
                tireId,
                vehicleId,
                request.getTireId(),
                request.getStatus(),
                request.getProductionYear(),
                request.getPurchaseDate(),
                request.getBrand(),
                request.getModel(),
                request.getWidth(),
                request.getProfile(),
                request.getDiameter(),
                request.getType(),
                request.getSpeedIndex(),
                request.getCapacityIndex(),
                request.getReinforced(),
                request.getRunOnFlat(),
                request.getSeasonId()
        ));
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
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionsData> actions = serviceActions.find(new ServiceActionsFilter(
                id,
                actionTypeName,
                workshop,
                dateFrom,
                dateTo,
                page,
                pageSize,
                false));
        return new ServiceActionsRequestGetResponse(actionsToResponse(actions));
    }

    @GetMapping(path = "/removed-vehicle-service-actions/", produces = "application/json; charset=UTF-8")
    public ServiceActionsRequestGetResponse removedVehicleServiceActions(
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionsData> actions = serviceActions.find(new ServiceActionsFilter(
                null,
                actionTypeName,
                workshop,
                dateFrom,
                dateTo,
                page,
                pageSize,
                true));
        return new ServiceActionsRequestGetResponse(actionsToResponse(actions));
    }

    @GetMapping(path = "/vehicle-service-action-value-summary/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionsSummaryValueGetResponse serviceActionsInvoiceSummary(
            @PathVariable Long id,
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        ServiceActionsInvoiceSummaryData summary =
                serviceActions.getActionInvoicesSummary(new ServiceActionsFilter(id,
                        actionTypeName,
                        workshop,
                        dateFrom,
                        dateTo,
                        false));
        return summaryToResponse(summary);
    }

    @GetMapping(path = "/vehicle-service-action/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionRequestGetResponse vehicleServiceActionById(
            @PathVariable Long id) {
        ServiceActionsData serviceAction = serviceActions.findById(new ServiceActionsFilter(id, false));
        return new ServiceActionRequestGetResponse(actionToResponse(serviceAction));
    }

    @PostMapping(path = "/vehicle-service-action", consumes = "application/json; charset=UTF-8")
    public void addVehicleServiceAction(@RequestBody ServiceActionPostRequest request) {
        BigDecimal netValue = DataValidationUtil.validateAndCreateValue(request.getInvoiceNetValue());
        BigDecimal taxRate = DataValidationUtil.validateAndCreateValue(request.getTaxRate());
        serviceActions.add(new ServiceActionsData(
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                netValue,
                taxRate,
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription()));
    }
//to put and restore
    @PutMapping(path = "/vehicle-service-action/{id}")
    public void updateServiceAction(@PathVariable Long id, @RequestBody ServiceActionPutRequest request) {
        BigDecimal netValue = DataValidationUtil.validateAndCreateValue(request.getInvoiceNetValue());
        BigDecimal taxRate = DataValidationUtil.validateAndCreateValue(request.getTaxRate());
        serviceActions.update(new ServiceActionsData(
                id,
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                netValue,
                taxRate,
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription(),
                request.getModifyDate(),
                null
        ));
    }

    @PutMapping(path = "/vehicle-service-action-to-remove/{id}")
    public void deleteServiceAction(@PathVariable Long id, @RequestBody ServiceActionPutRequest request) {
        BigDecimal netValue = DataValidationUtil.validateAndCreateValue(request.getInvoiceNetValue());
        BigDecimal taxRate = DataValidationUtil.validateAndCreateValue(request.getTaxRate());
        serviceActions.update(new ServiceActionsData(
                request.getId(),
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                netValue,
                taxRate,
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription(),
                request.getModifyDate(),
                request.getRemoveDate()
        ));
    }




    @GetMapping(path = "/workshops", produces = "application/json; charset=UTF-8")
    public WorkshopsRequestGetResponse workshops(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        List<WorkshopsData> workshops = workshopsService.findWithActionTypes(workshopsService.find(new WorkshopsFilter(name, city, page,
                pageSize, false)));
        return new WorkshopsRequestGetResponse(workshopsToResponse(workshops));
    }

    @GetMapping(path = "/removed-workshops", produces = "application/json; charset=UTF-8")
    public WorkshopsRequestGetResponse removedWorkshops(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<WorkshopsData> workshops = workshopsService.findWithActionTypes(workshopsService.find(new WorkshopsFilter(name, city, page,
                pageSize, true)));
        return new WorkshopsRequestGetResponse(workshopsToResponse(workshops));
    }

    @GetMapping(path = "/workshop/{id}", produces = "application/json; charset=UTF-8")
    public WorkshopRequestGetResponse workshopById(
            @PathVariable Long id) {
        WorkshopsData workshop = workshopsService.findWithActionTypes(workshopsService.find(new WorkshopsFilter(id))).get(0);
        return new WorkshopRequestGetResponse(workshopToResponse(workshop));
    }

    @PostMapping(path = "/workshop", consumes = "application/json; charset=UTF-8")
    public void addWorkshop(@RequestBody WorkshopPostRequest request) {
        workshopsService.add(new WorkshopsData(
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getActionTypes()));
    }

    @PutMapping(path = "/workshop/{id}", consumes = "application/json; charset=UTF-8")
    public void updateWorkshop(@PathVariable Long id, @RequestBody WorkshopPutRequest request) {
        workshopsService.update(new WorkshopsData(
                id,
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getActionTypes(),
                null));
    }

    @PutMapping(path = "/workshop-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeWorkshop(@PathVariable Long id, @RequestBody WorkshopPutRequest request) {
        workshopsService.update(new WorkshopsData(
                id,
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getActionTypes(),
                request.getRemoveDate()));
    }

    @GetMapping(path = "/workshop-service-types", produces = "application/json; charset=UTF-8")
    public WorkshopServiceTypesRequestGetResponse serviceTypes(@RequestParam(name = "workshop_id") Long workshopId) {
        List<WorkshopServiceTypeData> workshopServiceTypes = workshopServiceTypeService.find(new WorkshopServiceTypeFilter(workshopId));
        return new WorkshopServiceTypesRequestGetResponse(workshopServicesToResponse(workshopServiceTypes));
    }




    @GetMapping(path = "/service-action-types", produces = "application/json; charset=UTF-8")
    public ServiceActionTypesRequestGetResponse serviceActionTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionTypeData> serviceActionTypes =
                serviceActonTypes.find(new ServiceActionTypesFilter(page, pageSize,false));
        return new ServiceActionTypesRequestGetResponse(actionTypesToResponse(serviceActionTypes));
    }

    @GetMapping(path = "/removed-service-action-types", produces = "application/json; charset=UTF-8")
    public ServiceActionTypesRequestGetResponse removedServiceActionTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionTypeData> serviceActionTypes =
                serviceActonTypes.find(new ServiceActionTypesFilter(page, pageSize,true));
        return new ServiceActionTypesRequestGetResponse(actionTypesToResponse(serviceActionTypes));
    }

    @GetMapping(path = "/service-action-type/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionTypeRequestGetResponse serviceActionTypesById(
            @PathVariable Long id) {
        ServiceActionTypeData serviceActionType =
                serviceActonTypes.find(new ServiceActionTypesFilter(id, false)).get(0);
        return new ServiceActionTypeRequestGetResponse(actionTypeToResponse(serviceActionType));
    }

    @GetMapping(path = "/removed-service-action-type/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionTypeRequestGetResponse removedServiceActionTypesById(
            @PathVariable Long id) {
        ServiceActionTypeData serviceActionType = serviceActonTypes.find(new ServiceActionTypesFilter(id,true)).get(0);
        return new ServiceActionTypeRequestGetResponse(actionTypeToResponse(serviceActionType));
    }

    @PostMapping(path = "/service-action-type", consumes = "application/json; charset=UTF-8")
    public void addServiceActionType(@RequestBody ServiceActionTypesPostRequest request) {
        serviceActonTypes.add(new ServiceActionTypeData(request.getName()));
    }

    @PutMapping(path = "/service-action-type/{id}", consumes = "application/json; charset=UTF-8")
    public void updateServiceActionType(@PathVariable Long id,
                                        @RequestBody ServiceActionTypesPutRequest request) {
        serviceActonTypes.update(new ServiceActionTypeData(id, request.getName(),null));
    }

    @PutMapping(path = "/service-action-type-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeServiceActionType(@PathVariable Long id,
                                        @RequestBody ServiceActionTypesPutRequest request) {
        serviceActonTypes.update(new ServiceActionTypeData(id, request.getName(),request.getRemoveDate()));
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

    private List<VehicleGetResponse> vehiclesWithoutTiresToResponse(List<VehicleData> vehicles) {
        List<VehicleGetResponse> list = new ArrayList<>();
        for (VehicleData vehicle : vehicles) {
            list.add(new VehicleGetResponse(
                    vehicle.getId(),
                    vehicle.getBrandName(),
                    vehicle.getModelName(),
                    vehicle.getRegistrationNumber()
            ));
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
            list.add(new ServiceActionGetResponse(
                    data.getId(),
                    data.getVehicleId(),
                    data.getCarMileage(),
                    parseDate(data.getServiceDate()),
                    data.getInvoiceNumber(),
                    bigDecimalToString(data.getInvoiceGrossValue()),
                    bigDecimalToString(data.getTaxValue()),
                    bigDecimalToString(data.getTaxRate()),
                    bigDecimalToString(data.getInvoiceNetValue()),
                    data.getWorkshopId(),
                    data.getWorkshopName(),
                    data.getServiceActionTypeId(),
                    data.getServiceActionTypeName(),
                    data.getWorkshopsData(),
                    data.getServiceActionDescription(),
                    parseDateTime(data.getModifyDate()),
                    parseDateTime(data.getRemoveDate())));
        }
        return list;
    }

    private ServiceActionGetResponse actionToResponse(ServiceActionsData data) {
        return new ServiceActionGetResponse(
                data.getId(),
                data.getVehicleId(),
                data.getCarMileage(),
                parseDate(data.getServiceDate()),
                data.getInvoiceNumber(),
                bigDecimalToString(data.getInvoiceGrossValue()),
                bigDecimalToString(data.getTaxValue()),
                bigDecimalToString(data.getTaxRate()),
                bigDecimalToString(data.getInvoiceNetValue()),
                data.getWorkshopId(),
                data.getWorkshopName(),
                data.getServiceActionTypeId(),
                data.getServiceActionTypeName(),
                data.getWorkshopsData(),
                data.getServiceActionDescription(),
                parseDateTime(data.getModifyDate()),
                parseDateTime(data.getRemoveDate())
                );
    }

    private List<WorkshopGetResponse> workshopsToResponse(List<WorkshopsData> workshops) {
        List<WorkshopGetResponse> list = new ArrayList<>();
        for (WorkshopsData workshop : workshops) {
            list.add(new WorkshopGetResponse(
                    workshop.getId(),
                    workshop.getName(),
                    workshop.getTaxNumber(),
                    workshop.getStreet(),
                    workshop.getBuildingNo(),
                    workshop.getApartmentNo(),
                    workshop.getPostalCode(),
                    workshop.getCity(),
                    workshop.getActionTypes(),
                    workshop.getActionTypesList(),
                    parseDateTime(workshop.getRemoveDate()))
            );
        }
        return list;
    }

    private WorkshopGetResponse workshopToResponse(WorkshopsData workshop) {
        return new WorkshopGetResponse(
                workshop.getId(),
                workshop.getName(),
                workshop.getTaxNumber(),
                workshop.getStreet(),
                workshop.getBuildingNo(),
                workshop.getApartmentNo(),
                workshop.getPostalCode(),
                workshop.getCity(),
                workshop.getActionTypes(),
                workshop.getActionTypesList(),
                parseDateTime(workshop.getRemoveDate())
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
                    data.getType(),
                    data.getSpeedIndex(),
                    data.getCapacityIndex(),
                    data.getReinforced(),
                    data.getRunOnFlat(),
                    data.getSeasonId(),
                    data.getSeasonName()
            ));
        }
        return list;
    }

    private VehicleTiresGetResponse tireToResponse(VehicleTiresData data) {
        return new VehicleTiresGetResponse(
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
                data.getType(),
                data.getSpeedIndex(),
                data.getCapacityIndex(),
                data.getReinforced(),
                data.getRunOnFlat(),
                data.getSeasonId(),
                data.getSeasonName()
        );
    }

    private VehicleBrandGetResponse brandToResponse(VehicleBrandData brand) {
        return new VehicleBrandGetResponse(brand.getId(), brand.getName());
    }

    private VehicleModelGetResponse modelToResponse(VehicleModelData model) {
        return new VehicleModelGetResponse(model.getId(), model.getBrandId(), model.getName());
    }

    private ServiceActionsSummaryValueGetResponse summaryToResponse(ServiceActionsInvoiceSummaryData data) {
        return new ServiceActionsSummaryValueGetResponse(
                data.getSummaryNetValue(),
                data.getSummaryTaxValue(),
                data.getSummaryGrossValue());
    }

    private List<WorkshopServiceTypesGetResponse> workshopServicesToResponse(List<WorkshopServiceTypeData> workshopServices) {
        List<WorkshopServiceTypesGetResponse> list = new ArrayList<>();
        for (WorkshopServiceTypeData data : workshopServices) {
            list.add(new WorkshopServiceTypesGetResponse(
                    data.getId(),
                    data.getWorkshopId(),
                    data.getServiceActionTypeId(),
                    data.getServiceActionName()
            ));
        }
        return list;
    }

    private String parseDateTime(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        return date.toString();
    }

    private String parseDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.toString();
    }

    private String bigDecimalToString(BigDecimal value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

}
