package pl.com.chrzanowski.scaffolding.domain.contractortypes;

public class ContractorTypeFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;
    private Boolean isRemoved;

    public ContractorTypeFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ContractorTypeFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ContractorTypeFilter(Long id) {
        this.id = id;
    }

    public ContractorTypeFilter(String name) {
        this.name = name;
    }

    public ContractorTypeFilter(Long id, Boolean isRemoved) {
        this.id = id;
        this.isRemoved = isRemoved;
    }

    public ContractorTypeFilter(Long page, Long pageSize, Boolean isRemoved) {
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public ContractorTypeFilter() {
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
