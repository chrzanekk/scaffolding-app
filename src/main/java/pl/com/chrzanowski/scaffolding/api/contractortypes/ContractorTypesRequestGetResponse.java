package pl.com.chrzanowski.scaffolding.api.contractortypes;

import java.util.List;

public class ContractorTypesRequestGetResponse {

    private List<ContractorTypeGetResponse> contractorTypes;

    public ContractorTypesRequestGetResponse(List<ContractorTypeGetResponse> contractorTypes) {
        this.contractorTypes = contractorTypes;
    }

    public List<ContractorTypeGetResponse> getContractorTypes() {
        return contractorTypes;
    }
}
