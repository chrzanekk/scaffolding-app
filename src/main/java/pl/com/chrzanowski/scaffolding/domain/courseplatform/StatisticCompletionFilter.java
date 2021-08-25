package pl.com.chrzanowski.scaffolding.domain.courseplatform;

public class StatisticCompletionFilter {
    private Long page;
    private Long pageSize;

    public StatisticCompletionFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
