package pl.com.chrzanowski.scaffolding.domain.tires;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleTiresFilter {

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
    private Boolean runOnFlat;
    private Long seasonId;
    private String seasonName;

    private Long page;
    private Long pageSize;

    public VehicleTiresFilter(Long id, Long vehicleId) {
        this.id = id;
        this.vehicleId = vehicleId;
    }

    public VehicleTiresFilter(Long id) {
        this.id = id;
    }

    public VehicleTiresFilter(Long id, Long vehicleId, String status) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.status = status;
    }

    public VehicleTiresFilter(Long vehicleId, String status, Long page, Long pageSize) {
        this.vehicleId = vehicleId;
        this.status = status;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleTiresFilter(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public VehicleTiresFilter() {
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

    public Boolean isRunOnFlat() {
        return runOnFlat;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

}
