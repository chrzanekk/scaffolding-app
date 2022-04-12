package pl.com.chrzanowski.scaffolding.api.contractortypes;

public class ContractorTypePostRequest {

    private Long id;
    private String name;
    private String createDate;
    private String modifyDate;
    private String removeDate;
    private Long createUserId;
    private Long modifyUserId;
    private Long removeUserId;

    public ContractorTypePostRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
