package pl.com.chrzanowski.scaffolding.api.tires;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleTiresGetResponse {

    private Long id;
    private Long vehicleId;
    private Long tireId;
    private String status;
    private Integer productionYear;
    private String purchaseDate;

    private String brand;
    private String model;
    private Integer width;
    private Integer profile;
    private Integer diameter;
    private String type;
    private String speedIndex;
    private Integer capacityIndex;
    private String reinforced;
    private String runOnFlat;
    private Long seasonId;
    private String seasonName;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleTiresGetResponse(Long id,
                                   Long vehicleId,
                                   Long tireId,
                                   String status,
                                   Integer productionYear,
                                   LocalDate purchaseDate,
                                   String brand,
                                   String model,
                                   Integer width,
                                   Integer profile,
                                   Integer diameter,
                                   String type,
                                   String speedIndex,
                                   Integer capacityIndex,
                                   String reinforced,
                                   String runOnFlat,
                                   Long seasonId,
                                   String seasonName) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.tireId = tireId;
        this.status = status;
        this.productionYear = productionYear;
        this.purchaseDate = purchaseDate.toString();
        this.brand = brand;
        this.model = model;
        this.width = width;
        this.profile = profile;
        this.diameter = diameter;
        this.type = type;
        this.speedIndex = speedIndex;
        this.capacityIndex = capacityIndex;
        this.reinforced = reinforced;
        this.runOnFlat = runOnFlat;
        this.seasonId = seasonId;
        this.seasonName = seasonName;
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

    public Integer getProductionYear() {
        return productionYear;
    }

    public String getPurchaseDate() {
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

    public String getRunOnFlat() {
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
