package pl.com.chrzanowski.scaffolding.domain.tires;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleTiresData {

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
    private Boolean isRunOnFlat;
    private Long seasonId;
    private String seasonName;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;
    private String runOnFlat;


    public VehicleTiresData(Long id,
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
                            Long seasonId,
                            String seasonName,
                            String runOnFlat) {
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
        this.type = type;
        this.speedIndex = speedIndex;
        this.capacityIndex = capacityIndex;
        this.reinforced = reinforced;
        this.seasonId = seasonId;
        this.seasonName = seasonName;
        this.runOnFlat = runOnFlat;
    }

    public VehicleTiresData(Long vehicleId,
                            String brand,
                            String model,
                            Integer productionYear,
                            LocalDate purchaseDate,
                            Integer width,
                            Integer profile,
                            Integer diameter,
                            String type,
                            String speedIndex,
                            Integer capacityIndex,
                            String reinforced,
                            Boolean isRunOnFlat,
                            Long seasonId,
                            String status
    ) {
        this.vehicleId = vehicleId;
        this.status = status;
        this.productionYear = productionYear;
        this.purchaseDate = purchaseDate;
        this.brand = brand;
        this.model = model;
        this.width = width;
        this.profile = profile;
        this.diameter = diameter;
        this.type = type;
        this.speedIndex = speedIndex;
        this.capacityIndex = capacityIndex;
        this.reinforced = reinforced;
        this.isRunOnFlat = isRunOnFlat;
        this.seasonId = seasonId;
    }
    public VehicleTiresData(VehicleTiresData data,
                            String status) {
        this.id = data.getId();
        this.tireId = data.getTireId();
        this.vehicleId = data.getVehicleId();
        this.status = status;
        this.productionYear = data.getProductionYear();
        this.purchaseDate = data.getPurchaseDate();
        this.brand = data.getBrand();
        this.model = data.getModel();
        this.width = data.getWidth();
        this.profile = data.getProfile();
        this.diameter = data.getDiameter();
        this.type = data.getType();
        this.speedIndex = data.getSpeedIndex();
        this.capacityIndex = data.getCapacityIndex();
        this.reinforced = data.getReinforced();
        this.isRunOnFlat = data.isRunOnFlat();
        this.seasonId = data.getSeasonId();
        this.modifyDate = LocalDateTime.now();
    }

    public VehicleTiresData(Long id,
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
                            Boolean isRunOnFlat,
                            Long seasonId) {
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
        this.type = type;
        this.speedIndex = speedIndex;
        this.capacityIndex = capacityIndex;
        this.reinforced = reinforced;
        this.isRunOnFlat = isRunOnFlat;
        this.seasonId = seasonId;
        this.modifyDate = LocalDateTime.now();
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
