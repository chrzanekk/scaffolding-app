package pl.com.chrzanowski.scaffolding.domain;

public class BrandsAndModelsFilter {
    private Long brandId;
    private Long modelId;
    private String brandName;
    private String modelName;
    private Long page;
    private Long pageSize;

    public BrandsAndModelsFilter() {
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
