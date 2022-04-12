package pl.com.chrzanowski.scaffolding.api.vehiclebrands;

import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;

import java.time.LocalDateTime;

public class VehicleBrandGetResponse {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleBrandGetResponse(Long id, String name, LocalDateTime createDate, LocalDateTime modifyDate, LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }

    public VehicleBrandGetResponse(VehicleBrandData vehicleBrandData) {
        this.id = vehicleBrandData.getId();
        this.name = vehicleBrandData.getName();
    }

    public VehicleBrandGetResponse(Long id, String name) {
        this.id = id;
        this.name = name;
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
