package pl.com.chrzanowski.scaffolding.domain.vehicletypes;

public class VehicleTypeFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public VehicleTypeFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleTypeFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleTypeFilter(Long id, Long page, Long pageSize) {
        this.id = id;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleTypeFilter(String name) {
        this.name = name;
    }

    public VehicleTypeFilter() {
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
