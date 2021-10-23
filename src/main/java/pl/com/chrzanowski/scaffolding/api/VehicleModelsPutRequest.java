package pl.com.chrzanowski.scaffolding.api;

import java.time.LocalDateTime;

public class VehicleModelsPutRequest {

    private Long modelId;
    private String modelName;
    private Long brandId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleModelsPutRequest() {
    }

    public Long getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public Long getBrandId() {
        return brandId;
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
