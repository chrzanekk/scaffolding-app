package pl.com.chrzanowski.scaffolding.domain;

public class CoreFieldsFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;
    private Boolean isRemoved;

    public CoreFieldsFilter(Long id, String name, Long page, Long pageSize, Boolean isRemoved) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public CoreFieldsFilter(Long page, Long pageSize, Boolean isRemoved) {
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public CoreFieldsFilter(String name, Long page, Long pageSize, Boolean isRemoved) {
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public CoreFieldsFilter(Long id) {
        this.id = id;
    }

    public CoreFieldsFilter(Long id, Boolean isRemoved) {
        this.id = id;
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
