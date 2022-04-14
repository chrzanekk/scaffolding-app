package pl.com.chrzanowski.scaffolding.api.currency;

public class CurrencyGetResponse {
    private Long id;
    private String name;
    private String code;
    private String createDate;
    private String modifyDate;
    private String removeDate;
    private Long createUserId;
    private Long modifyUserId;
    private Long removeUserId;

    public CurrencyGetResponse(Long id,
                               String name,
                               String code,
                               String createDate,
                               String modifyDate,
                               String removeDate,
                               Long createUserId,
                               Long modifyUserId,
                               Long removeUserId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
        this.createUserId = createUserId;
        this.modifyUserId = modifyUserId;
        this.removeUserId = removeUserId;
    }
}
