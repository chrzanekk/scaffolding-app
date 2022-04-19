package pl.com.chrzanowski.scaffolding.api.contractors;

import java.util.List;

public class ContractorCurrencyListRequestGetResponse {

    private List<ContractorCurrencyGetResponse> contractorCurrencyList;

    public ContractorCurrencyListRequestGetResponse(List<ContractorCurrencyGetResponse> contractorCurrencyList) {
        this.contractorCurrencyList = contractorCurrencyList;
    }

    public List<ContractorCurrencyGetResponse> getContractorCurrencyList() {
        return contractorCurrencyList;
    }
}
