package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class VehicleModelData {

    private Long id;
    private Long brandId;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleModelData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VehicleModelData(String name) {
        this.name = name;
    }

    public VehicleModelData(Long id, Long brandId, String name) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
    }

    public VehicleModelData(Long id, String name, LocalDateTime modifyDate) {
        this.id = id;
        this.name = name;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getName() {
        return name;
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
