package pl.com.chrzanowski.scaffolding.api.currency;

public class CurrencyPutRequest {

    private Long id;
    private String name;
    private String code;
    private String createDate;
    private String modifyDate;
    private String removeDate;
    private Long createUserId;
    private Long modifyUserId;
    private Long removeUserId;

    public CurrencyPutRequest(Long id,
                              String name,
                              String code,
                              Long modifyUserId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.modifyUserId = modifyUserId;
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

    public String getCreateDate() {
        return createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public Long getRemoveUserId() {
        return removeUserId;
    }
}
