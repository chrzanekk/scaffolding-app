package pl.com.chrzanowski.scaffolding.domain.serviceactiontypes;

public class ServiceActionTypesFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;
    private Boolean isRemoved;

    public ServiceActionTypesFilter() {
    }

    public ServiceActionTypesFilter(Long id) {
        this.id = id;
    }

    public ServiceActionTypesFilter(String name) {
        this.name = name;
    }

    public ServiceActionTypesFilter(Long id, Boolean isRemoved) {
        this.id = id;
        this.isRemoved = isRemoved;
    }

    public ServiceActionTypesFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ServiceActionTypesFilter(Long page, Long pageSize, Boolean isRemoved) {
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
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

    public Boolean getIsRemoved() {
        return isRemoved;
    }
}
