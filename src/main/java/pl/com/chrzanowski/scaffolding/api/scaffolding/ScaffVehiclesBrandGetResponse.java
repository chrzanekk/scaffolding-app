package pl.com.chrzanowski.scaffolding.api.scaffolding;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleBrandData;

import java.time.LocalDateTime;

public class ScaffVehiclesBrandGetResponse {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ScaffVehiclesBrandGetResponse(Long id, String name, LocalDateTime createDate, LocalDateTime modifyDate, LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }

    public ScaffVehiclesBrandGetResponse(ScaffVehicleBrandData scaffVehicleBrandData) {
        this.id = scaffVehicleBrandData.getId();
        this.name = scaffVehicleBrandData.getName();
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
