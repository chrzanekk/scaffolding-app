package pl.com.chrzanowski.scaffolding.api.contractors;

public class ContractorRequestGetResponse {

    private ContractorGetResponse contractor;

    public ContractorRequestGetResponse(ContractorGetResponse contractor) {
        this.contractor = contractor;
    }

    public ContractorGetResponse getContractor() {
        return contractor;
    }
}
