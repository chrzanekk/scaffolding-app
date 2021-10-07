package pl.com.chrzanowski.scaffolding.domain;

public class ServiceActionTypesFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public ServiceActionTypesFilter() {
    }

    public ServiceActionTypesFilter(Long id) {
        this.id = id;
    }

    public ServiceActionTypesFilter(Long page, Long pageSize) {
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
