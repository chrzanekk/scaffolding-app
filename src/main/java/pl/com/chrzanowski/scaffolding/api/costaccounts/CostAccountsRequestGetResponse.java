package pl.com.chrzanowski.scaffolding.api.costaccounts;

import java.util.List;

public class CostAccountsRequestGetResponse {

    private List<CostAccountGetResponse> costAccounts;

    public CostAccountsRequestGetResponse(List<CostAccountGetResponse> costAccounts) {
        this.costAccounts = costAccounts;
    }

    public List<CostAccountGetResponse> getCostAccounts() {
        return costAccounts;
    }
}
