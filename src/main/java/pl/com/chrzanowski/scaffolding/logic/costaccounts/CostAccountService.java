package pl.com.chrzanowski.scaffolding.logic.costaccounts;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountData;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountFilter;
import pl.com.chrzanowski.scaffolding.logic.ICostAccounts;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getDateTime;

@Service
public class CostAccountService implements ICostAccounts {

    private CostAccountsJdbcRepository costAccountsJdbcRepository;

    public CostAccountService(CostAccountsJdbcRepository costAccountsJdbcRepository) {
        this.costAccountsJdbcRepository = costAccountsJdbcRepository;
    }

    @Override
    public Long add(CostAccountData data) {
        validateData(data);
        return costAccountsJdbcRepository.create(data);
    }

    @Override
    public void update(CostAccountData data) {
        validateData(data);
        costAccountsJdbcRepository.update(data);
    }

    @Override
    public List<CostAccountData> find(CostAccountFilter filter) {
        return getCostAccounts(costAccountsJdbcRepository.find(filter));
    }

    @Override
    public void remove(CostAccountData data) {
        costAccountsJdbcRepository.remove(data);
    }

    private List<CostAccountData> getCostAccounts(List<Map<String, Object>> data) {
        List<CostAccountData> list = new ArrayList<>();
        for(Map<String,Object> row : data) {
            list.add(new CostAccountData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getDateTime(row, "create_date"),
                    getDateTime(row, "modify_date"),
                    getDateTime(row, "remove_date"),
                    getLong(row, "create_user_id"),
                    getLong(row, "modify_user_id"),
                    getLong(row, "remove_user_id")
            ));
        }
        return list;
    }

    private void validateData(CostAccountData data) {
        DataValidationUtil.validateTextField(data.getName(), "Konto kosztowe/budowa");
    }
}
