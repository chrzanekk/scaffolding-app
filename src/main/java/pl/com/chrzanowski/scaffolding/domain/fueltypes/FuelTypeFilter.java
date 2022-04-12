package pl.com.chrzanowski.scaffolding.domain.fueltypes;

public class FuelTypeFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public FuelTypeFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public FuelTypeFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
    public FuelTypeFilter(String name) {
        this.name = name;
    }

    public FuelTypeFilter() {
    }

    public FuelTypeFilter(Long id, Long page, Long pageSize) {
        this.id = id;
        this.page = page;
        this.pageSize = pageSize;
    }

    public FuelTypeFilter(Long id) {
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
