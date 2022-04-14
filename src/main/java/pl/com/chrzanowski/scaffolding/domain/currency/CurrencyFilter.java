package pl.com.chrzanowski.scaffolding.domain.currency;

public class CurrencyFilter {

    private Long id;
    private String name;
    private String code;
    private Long page;
    private Long pageSize;
    private Boolean isRemoved;

    public CurrencyFilter(Long id, String name, String code, Long page, Long pageSize, Boolean isRemoved) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public CurrencyFilter(Long page, Long pageSize, Boolean isRemoved) {
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public CurrencyFilter(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
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
