package pl.com.chrzanowski.scaffolding.domain;

public class VehicleBrandFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public VehicleBrandFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleBrandFilter(Long id) {
        this.id = id;
    }

    public VehicleBrandFilter(String name) {
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
