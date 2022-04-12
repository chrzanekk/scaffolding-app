package pl.com.chrzanowski.scaffolding.api.contractortypes;

public class ContractorTypeRequestGetResponse {

    private ContractorTypeGetResponse contractorType;

    public ContractorTypeRequestGetResponse(ContractorTypeGetResponse contractorType) {
        this.contractorType = contractorType;
    }

    public ContractorTypeGetResponse getContractorType() {
        return contractorType;
    }
}
