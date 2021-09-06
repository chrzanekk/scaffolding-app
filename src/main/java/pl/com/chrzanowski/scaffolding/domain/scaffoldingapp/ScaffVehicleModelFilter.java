package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleModelFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public ScaffVehicleModelFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleModelFilter(Long id) {
        this.id = id;
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
