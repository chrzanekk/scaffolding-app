package pl.com.chrzanowski.scaffolding.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleTiresPostRequest {

    private Long id;
    private Long vehicleId;
    private Long tireId;
    private String status;
    private String oldStatus;
    private Integer productionYear;
    private LocalDate purchaseDate;

    private String brand;
    private String model;
    private Integer width;
    private Integer profile;
    private Integer diameter;
    private String type;
    private String speedIndex;
    private Integer capacityIndex;
    private String reinforced;
    private Boolean runOnFlat;
    private Long seasonId;
    private String seasonName;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleTiresPostRequest() {
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Long getTireId() {
        return tireId;
    }

    public String getStatus() {
        return status;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getProfile() {
        return profile;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public String getType() {
        return type;
    }

    public String getSpeedIndex() {
        return speedIndex;
    }

    public Integer getCapacityIndex() {
        return capacityIndex;
    }

    public String getReinforced() {
        return reinforced;
    }

    public Boolean getRunOnFlat() {
        return runOnFlat;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public String getSeasonName() {
        return seasonName;
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
