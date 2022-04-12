package pl.com.chrzanowski.scaffolding.api.vehicles;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleGetResponse {

    private Long id;
    private String registrationNumber;
    private String vin;
    private Integer productionYear;
    private String firstRegistrationDate;
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
    private Long brandId;
    private Long modelId;

    public VehicleGetResponse(Long id,
                              String brandName,
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
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.productionYear = productionYear;
        this.firstRegistrationDate = firstRegistrationDate.toString();
        this.freePlacesForTechnicalInspections = freePlacesForTechnicalInspections;
        this.brandName = brandName;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public VehicleGetResponse(Long id, String registrationNumber, String brandName, String modelName) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brandName = brandName;
        this.modelName = modelName;
    }

    public Long getId() {
        return id;
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

    public String getFirstRegistrationDate() {
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

    public Float getLength() {
        return length;
    }

    public Float getWidth() {
        return width;
    }

    public Float getHeight() {
        return height;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getModelId() {
        return modelId;
    }
}
