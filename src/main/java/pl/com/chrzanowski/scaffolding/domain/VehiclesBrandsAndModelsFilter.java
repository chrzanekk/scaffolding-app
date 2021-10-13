package pl.com.chrzanowski.scaffolding.domain;

public class VehiclesBrandsAndModelsFilter {
    private Long brandId;
    private Long modelId;
    private String brandName;
    private String modelName;
    private Long page;
    private Long pageSize;

    public VehiclesBrandsAndModelsFilter() {
    }

    public VehiclesBrandsAndModelsFilter(Long brandId, Long modelId, String brandName, String modelName) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.brandName = brandName;
        this.modelName = modelName;
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

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
