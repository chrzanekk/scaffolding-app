package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScaffVehicleData {

    private Long id;
    private ScaffVehicleBrandData brand;
    private ScaffVehicleModelData model;
    private Long brandId;
    private Long modelId;
    private String registrationNumber;
    private String vin;
    private Integer productionYear;
    private LocalDate firstRegistrationDate;
    private Integer freePlacesForTechnicalInspections;
    private Long fuelTypeId;
    private Long vehicleTypeId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    private String brandName;
    private String modelName;
    private String fuelType;
    private String vehicleType;


    public ScaffVehicleData(Long id, String registrationNumber, String vin, Integer productionYear, LocalDate firstRegistrationDate, Integer freePlacesForTechnicalInspections, Long fuelTypeId, Long vehicleTypeId, LocalDateTime createDate, LocalDateTime modifyDate, LocalDateTime removeDate) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }

    public ScaffVehicleData(Long id, String registrationNumber, String vin, Integer productionYear, LocalDate firstRegistrationDate, Integer freePlacesForTechnicalInspections, Long fuelTypeId, Long vehicleTypeId) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
    }

    public ScaffVehicleData(Long id, ScaffVehicleBrandData brand, ScaffVehicleModelData model, String registrationNumber, String vin, Integer productionYear, LocalDate firstRegistrationDate, Integer freePlacesForTechnicalInspections, Long fuelTypeId, Long vehicleTypeId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
    }


    public ScaffVehicleData(String brandName, String modelName, String registrationNumber) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.registrationNumber = registrationNumber;
    }

    public ScaffVehicleData(Long id, String brandName, String modelName, String registrationNumber, String vin, Integer productionYear,
                            LocalDate firstRegistrationDate, Integer freePlacesForTechnicalInspections, String fuelType, String vehicleType) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
    }

    public ScaffVehicleData(Long id, Long brandId, Long modelId, String registrationNumber, String vin, Integer productionYear, LocalDate firstRegistrationDate, Integer freePlacesForTechnicalInspections, Long fuelTypeId, Long vehicleTypeId) {
        this.id = id;
        this.brandId = brandId;
        this.modelId = modelId;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
    }

    public ScaffVehicleData(String brandName,
                            String modelName,
                            String registrationNumber,
                            String vin,
                            Integer productionYear,
                            LocalDate firstRegistrationDate,
                            Integer freePlacesForTechnicalInspections,
                            Long fuelTypeId,
                            Long vehicleTypeId) {
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.brandName = brandName;
        this.modelName = modelName;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.createDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public ScaffVehicleBrandData getBrand() {
        return brand;
    }

    public ScaffVehicleModelData getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVin() {
        return vin;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public LocalDate getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public Integer getFreePlacesForTechnicalInspections() {
        return freePlacesForTechnicalInspections;
    }

    public Long getFuelTypeId() {
        return fuelTypeId;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getModelId() {
        return modelId;
    }
}
