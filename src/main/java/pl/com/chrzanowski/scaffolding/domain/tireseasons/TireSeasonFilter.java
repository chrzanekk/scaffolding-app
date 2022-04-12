package pl.com.chrzanowski.scaffolding.domain.tireseasons;

public class TireSeasonFilter {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;

    public TireSeasonFilter(Long id, String name, Long page, Long pageSize) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public TireSeasonFilter() {
    }

    public TireSeasonFilter(Long page, Long pageSize) {
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
