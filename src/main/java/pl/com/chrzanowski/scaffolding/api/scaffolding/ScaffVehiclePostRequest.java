package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScaffVehiclePostRequest {

    private Long id;
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
    private Long brandId;
    private Long modelId;

    public ScaffVehiclePostRequest() {
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
