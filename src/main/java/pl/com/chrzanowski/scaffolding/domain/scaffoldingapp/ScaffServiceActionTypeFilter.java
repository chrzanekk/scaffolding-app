package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffServiceActionTypeFilter {

    private Long id;
    private String name;
    private String description;
    private Long page;
    private Long pageSize;

    public ScaffServiceActionTypeFilter() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
