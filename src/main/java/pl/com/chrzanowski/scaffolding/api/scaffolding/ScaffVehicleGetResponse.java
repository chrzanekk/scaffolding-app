package pl.com.chrzanowski.scaffolding.api.scaffolding;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleModelData;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScaffVehicleGetResponse {

    private Long id;
    private ScaffVehicleBrandData brand;
    private ScaffVehicleModelData model;
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

    public ScaffVehicleGetResponse(ScaffVehicleData scaffVehicleData) {
        this.id = scaffVehicleData.getId();
        this.brand = scaffVehicleData.getBrand();
        this.model = scaffVehicleData.getModel();
        this.registrationNumber = scaffVehicleData.getRegistrationNumber();
        this.vin = scaffVehicleData.getVin();
        this.productionYear = scaffVehicleData.getProductionYear();
        this.firstRegistrationDate = scaffVehicleData.getFirstRegistrationDate();
        this.freePlacesForTechnicalInspections = scaffVehicleData.getFreePlacesForTechnicalInspections();
        this.fuelTypeId = scaffVehicleData.getFuelTypeId();
        this.vehicleTypeId = scaffVehicleData.getVehicleTypeId();
        this.createDate = scaffVehicleData.getCreateDate();
        this.modifyDate = scaffVehicleData.getModifyDate();
        this.removeDate = scaffVehicleData.getRemoveDate();
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
}
