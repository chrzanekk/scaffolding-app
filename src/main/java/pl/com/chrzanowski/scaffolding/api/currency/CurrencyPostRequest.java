package pl.com.chrzanowski.scaffolding.api.currency;

public class CurrencyPostRequest {
    private String name;
    private String code;
    private Long createUserId;


    public CurrencyPostRequest(String name, String code, Long createUserId) {
        this.name = name;
        this.code = code;
        this.createUserId = createUserId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Long getCreateUserId() {
        return createUserId;
    }
}
