package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffVehicleModelData {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public ScaffVehicleModelData(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleModelData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScaffVehicleModelData(Long id, Long page, Long pageSize) {
        this.id = id;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffVehicleModelData(String name) {
        this.name = name;
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
