package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffFuelTypeFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public ScaffFuelTypeFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffFuelTypeFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
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
