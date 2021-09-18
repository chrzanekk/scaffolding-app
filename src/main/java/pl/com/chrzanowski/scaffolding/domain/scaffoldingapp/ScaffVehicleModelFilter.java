package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleModelFilter {

    private Long id;
    private Long brandId;
    private String name;
    private Long page;
    private Long pageSize;

    public ScaffVehicleModelFilter(Long id, Long  brandId, String name, Long page, Long pageSize) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleModelFilter(Long id) {
        this.id = id;
    }

    public ScaffVehicleModelFilter(String name) {
        this.name = name;
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

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
