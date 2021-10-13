package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class VehiclesBrandsAndModelsData {
    private Long brandId;
    private Long modelId;
    private String brandName;
    private String modelName;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;


    public VehiclesBrandsAndModelsData(Long brandId, Long modelId, String brandName, String modelName) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.brandName = brandName;
        this.modelName = modelName;
    }

    public VehiclesBrandsAndModelsData(String brandName, String modelName) {
        this.brandName = brandName;
        this.modelName = modelName;
    }

    public VehiclesBrandsAndModelsData(Long brandId, Long modelId, String brandName, String modelName,
                                       LocalDateTime modifyDate) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.brandName = brandName;
        this.modelName = modelName;
        this.modifyDate = modifyDate;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getModelId() {
        return modelId;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
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
