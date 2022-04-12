package pl.com.chrzanowski.scaffolding.domain.vehicles;


import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.vehiclemodels.VehicleModelData;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleData {

    private Long id;
    private VehicleBrandData brand;
    private VehicleModelData model;
    private Long brandId;
    private Long modelId;
    private String registrationNumber;
    private String vin;
    private Integer productionYear;
    private LocalDate firstRegistrationDate;
    private Integer freePlacesForTechnicalInspections;
    private Long fuelTypeId;
    private Long vehicleTypeId;
    private Float length;
    private Float width;
    private Float height;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    private String brandName;
    private String modelName;
    private String fuelType;
    private String vehicleType;


    public VehicleData(Long id,
                       Long brandId,
                       String brandName,
                       Long modelId,
                       String modelName,
                       String registrationNumber,
                       String vin,
                       Integer productionYear,
                       LocalDate firstRegistrationDate,
                       Integer freePlacesForTechnicalInspections,
                       String fuelType,
                       String vehicleType,
                       Float length,
                       Float width,
                       Float height) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.modelId = modelId;
        this.modelName = modelName;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public VehicleData(Long id,
                       String brandName,
                       String modelName,
                       String registrationNumber,
                       String vin,
                       Integer productionYear,
                       LocalDate firstRegistrationDate,
                       Integer freePlacesForTechnicalInspections,
                       Long fuelTypeId,
                       Long vehicleTypeId,
                       Float length,
                       Float width,
                       Float height) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.brandName = brandName;
        this.modelName = modelName;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.modifyDate = LocalDateTime.now();
    }

    public VehicleData(String brandName,
                       String modelName,
                       String registrationNumber,
                       String vin,
                       Integer productionYear,
                       LocalDate firstRegistrationDate,
                       Integer freePlacesForTechnicalInspections,
                       Long fuelTypeId,
                       Long vehicleTypeId,
                       Float length,
                       Float width,
                       Float height) {
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.brandName = brandName;
        this.modelName = modelName;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.createDate = LocalDateTime.now();
    }

    public VehicleData(Long brandId,
                       Long modelId,
                       String registrationNumber,
                       String vin,
                       Integer productionYear,
                       LocalDate firstRegistrationDate,
                       Integer freePlacesForTechnicalInspections,
                       Long fuelTypeId,
                       Long vehicleTypeId,
                       Float length,
                       Float width,
                       Float height) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate;
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.fuelTypeId = fuelTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.createDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public VehicleData(Long id, String registrationNumber, String brandName, String modelName) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brandName = brandName;
        this.modelName = modelName;
    }

    public VehicleBrandData getBrand() {
        return brand;
    }

    public VehicleModelData getModel() {
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

    public Float getLength() {
        return length;
    }

    public Float getWidth() {
        return width;
    }

    public Float getHeight() {
        return height;
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
