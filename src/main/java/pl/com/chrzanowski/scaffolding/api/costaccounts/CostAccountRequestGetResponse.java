package pl.com.chrzanowski.scaffolding.api.costaccounts;

public class CostAccountRequestGetResponse {

    private CostAccountGetResponse costAccount;

    public CostAccountRequestGetResponse(CostAccountGetResponse costAccount) {
        this.costAccount = costAccount;
    }

    public CostAccountGetResponse getCostAccount() {
        return costAccount;
    }
}
