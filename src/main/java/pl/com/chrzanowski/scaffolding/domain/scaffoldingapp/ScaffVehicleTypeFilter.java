package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleTypeFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public ScaffVehicleTypeFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleTypeFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleTypeFilter(Long id, Long page, Long pageSize) {
        this.id = id;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleTypeFilter(String name) {
        this.name = name;
    }

    public ScaffVehicleTypeFilter() {
    }

    public Long getId() {
        return id;
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
