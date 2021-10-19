package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleTiresData {

    private Long id;
    private Long vehicleId;
    private Long tireId;
    private String status;
    private Integer productionYear;
    private LocalDate purchaseDate;

    private String brand;
    private String model;
    private Integer width;
    private Integer profile;
    private Integer diameter;
    private String speedIndex;
    private Integer capacityIndex;
    private String reinforced;
    private Boolean isRunOnFlat;
    private Long seasonId;
    private String seasonName;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;
    private String runOnFlat;

    public VehicleTiresData() {
    }

    public VehicleTiresData(Long id, Long vehicleId, Long tireId, String status, Integer productionYear,
                            LocalDate purchaseDate, String brand, String model, Integer width, Integer profile,
                            Integer diameter, String speedIndex, Integer capacityIndex, String reinforced,
                            Boolean isRunOnFlat, Long seasonId, String seasonName) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.tireId = tireId;
        this.status = status;
        this.productionYear = productionYear;
        this.purchaseDate = purchaseDate;
        this.brand = brand;
        this.model = model;
        this.width = width;
        this.profile = profile;
        this.diameter = diameter;
        this.speedIndex = speedIndex;
        this.capacityIndex = capacityIndex;
        this.reinforced = reinforced;
        this.isRunOnFlat = isRunOnFlat;
        this.seasonId = seasonId;
        this.seasonName = seasonName;
    }

    public VehicleTiresData(Long id, Long vehicleId, Long tireId, String status, Integer productionYear, LocalDate purchaseDate, String brand, String model, Integer width, Integer profile, Integer diameter, String speedIndex, Integer capacityIndex, String reinforced, Long seasonId, String seasonName, String runOnFlat) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.tireId = tireId;
        this.status = status;
        this.productionYear = productionYear;
        this.purchaseDate = purchaseDate;
        this.brand = brand;
        this.model = model;
        this.width = width;
        this.profile = profile;
        this.diameter = diameter;
        this.speedIndex = speedIndex;
        this.capacityIndex = capacityIndex;
        this.reinforced = reinforced;
        this.seasonId = seasonId;
        this.seasonName = seasonName;
        this.runOnFlat = runOnFlat;
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

    public String getSpeedIndex() {
        return speedIndex;
    }

    public Integer getCapacityIndex() {
        return capacityIndex;
    }

    public String getReinforced() {
        return reinforced;
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

    public Boolean isRunOnFlat() {
        return isRunOnFlat;
    }

    public String getRunOnFlat() {
        return runOnFlat;
    }
}
