package pl.com.chrzanowski.scaffolding.domain.currency;

public class CurrencyFilter {

    private Long id;
    private String namePl;
    private String nameEn;
    private String code;
    private Long page;
    private Long pageSize;
    private Boolean isRemoved;

    public CurrencyFilter(Long id, String namePl, String nameEn, String code, Long page, Long pageSize, Boolean isRemoved) {
        this.id = id;
        this.namePl = namePl;
        this.nameEn = nameEn;
        this.code = code;
        this.page = page;
        this.pageSize = pageSize;
        this.isRemoved = isRemoved;
    }

    public Long getId() {
        return id;
    }

    public String getNamePl() {
        return namePl;
    }

    public String getNameEn() {
        return nameEn;
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

    public Boolean getRemoved() {
        return isRemoved;
    }
}
