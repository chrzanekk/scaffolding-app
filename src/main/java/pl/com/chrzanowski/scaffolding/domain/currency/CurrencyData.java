package pl.com.chrzanowski.scaffolding.domain.currency;

public class CurrencyData {

    private Long id;
    private String code;
    private String nameEn;
    private String namePl;

    public CurrencyData(Long id, String code, String nameEn, String namePl) {
        this.id = id;
        this.code = code;
        this.nameEn = nameEn;
        this.namePl = namePl;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNamePl() {
        return namePl;
    }
}
