package pl.com.chrzanowski.scaffolding.api.contractortypes;

public class ContractorTypePostRequest {

    private String name;
    private Long createUserId;

    public ContractorTypePostRequest(String name, Long createUserId) {
        this.name = name;
        this.createUserId = createUserId;
    }

    public String getName() {
        return name;
    }

    public Long getCreateUserId() {
        return createUserId;
    }
}
