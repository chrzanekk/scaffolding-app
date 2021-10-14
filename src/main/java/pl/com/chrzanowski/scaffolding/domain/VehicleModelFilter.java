package pl.com.chrzanowski.scaffolding.domain;

public class VehicleModelFilter {

    private Long id;
    private Long brandId;
    private String name;
    private Long page;
    private Long pageSize;

    public VehicleModelFilter(Long id, Long  brandId, String name, Long page, Long pageSize) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public VehicleModelFilter(Long id, Long brandId) {
        this.id = id;
        this.brandId = brandId;
    }

    public VehicleModelFilter() {
    }

    public VehicleModelFilter(Long id) {
        this.id = id;
    }

    public VehicleModelFilter(String name) {
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
