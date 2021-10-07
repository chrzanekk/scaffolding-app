package pl.com.chrzanowski.scaffolding.domain;

public class VehicleModelData {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public VehicleModelData(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleModelData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VehicleModelData(Long id, Long page, Long pageSize) {
        this.id = id;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleModelData(String name) {
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
