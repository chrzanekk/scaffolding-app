package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehiclesFilter {

    private String registrationNumber;
    private Long brandId;
    private Long modelId;
    private Long page;
    private Long pageSize;

    public ScaffVehiclesFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehiclesFilter(String registrationNumber) {
        this.registrationNumber =registrationNumber;
    }

    public ScaffVehiclesFilter() {

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
}
