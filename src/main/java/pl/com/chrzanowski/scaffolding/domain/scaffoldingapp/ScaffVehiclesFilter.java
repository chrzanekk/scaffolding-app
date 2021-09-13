package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehiclesFilter {

    private Long id;
    private String registrationNumber;
    private String brandName;
    private String modelName;
    private Long brandId;
    private Long modelId;
    private Long page;
    private Long pageSize;

    public ScaffVehiclesFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehiclesFilter(Long id, Long page, Long pageSize) {
        this.id = id;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehiclesFilter(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getModelId() {
        return modelId;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
    }
}
