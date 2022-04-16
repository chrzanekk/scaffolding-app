package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountData;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountFilter;

import java.util.List;

public interface ICostAccounts {

    Long add(CostAccountData data);
    void update(CostAccountData data);
    List<CostAccountData> find(CostAccountFilter filter);
    void remove(CostAccountData data);
}
