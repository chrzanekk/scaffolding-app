package pl.com.chrzanowski.scaffolding.api;

import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;

import java.time.LocalDateTime;

public class VehicleModelGetResponse {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleModelGetResponse(Long id, String name, LocalDateTime createDate, LocalDateTime modifyDate, LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }
    public VehicleModelGetResponse(VehicleModelData vehicleModelData) {
        this.id = vehicleModelData.getId();
        this.name = vehicleModelData.getName();
    }

    public Long getId() {
        return id;
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
