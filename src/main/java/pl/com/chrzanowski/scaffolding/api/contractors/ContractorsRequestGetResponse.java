package pl.com.chrzanowski.scaffolding.api.contractors;

import java.util.List;

public class ContractorsRequestGetResponse {

    private List<ContractorGetResponse> contractors;

    public ContractorsRequestGetResponse(List<ContractorGetResponse> contractors) {
        this.contractors = contractors;
    }

    public List<ContractorGetResponse> getContractors() {
        return contractors;
    }
}
