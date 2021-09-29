package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffServiceActionTypesFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public ScaffServiceActionTypesFilter() {
    }

    public ScaffServiceActionTypesFilter(Long id) {
        this.id = id;
    }

    public ScaffServiceActionTypesFilter(Long page, Long pageSize) {
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
