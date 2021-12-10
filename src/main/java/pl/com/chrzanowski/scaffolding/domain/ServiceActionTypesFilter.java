package pl.com.chrzanowski.scaffolding.domain;

public class ServiceActionTypesFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;
    private Boolean itContainsRemoveDate;

    public ServiceActionTypesFilter() {
    }

    public ServiceActionTypesFilter(Long id) {
        this.id = id;
    }

    public ServiceActionTypesFilter(String name) {
        this.name = name;
    }

    public ServiceActionTypesFilter(Long id, Boolean itContainsRemoveDate) {
        this.id = id;
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public ServiceActionTypesFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ServiceActionTypesFilter(Long page, Long pageSize, Boolean itContainsRemoveDate) {
        this.page = page;
        this.pageSize = pageSize;
        this.itContainsRemoveDate = itContainsRemoveDate;
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

    public Boolean getItContainsRemoveDate() {
        return itContainsRemoveDate;
    }
}
