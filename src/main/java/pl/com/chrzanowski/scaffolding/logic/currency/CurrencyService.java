package pl.com.chrzanowski.scaffolding.logic.currency;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeData;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyData;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyFilter;
import pl.com.chrzanowski.scaffolding.logic.ICurrency;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;

@Service
public class CurrencyService implements ICurrency {

    private CurrencyJdbcRepository currencyJdbcRepository;

    public CurrencyService(CurrencyJdbcRepository currencyJdbcRepository) {
        this.currencyJdbcRepository = currencyJdbcRepository;
    }


    @Override
    public Long create(CurrencyData data) {
        validateData(data);
        return currencyJdbcRepository.create(data);
    }

    @Override
    public void update(CurrencyData data) {
        validateData(data);
        currencyJdbcRepository.update(data);
    }

    @Override
    public void remove(CurrencyData data) {
        currencyJdbcRepository.remove(data);
    }

    @Override
    public List<CurrencyData> find(CurrencyFilter filter) {
        return getCurrency(currencyJdbcRepository.find(filter));
    }

    private List<CurrencyData> getCurrency(List<Map<String, Object>> data) {
        List<CurrencyData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new CurrencyData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getString(row, "code"),
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

    private void validateData(CurrencyData data) {
        DataValidationUtil.validateTextField(data.getName(), "Nazwa waluty nie może być pusta");
        DataValidationUtil.validateTextField(data.getCode(), "Kod waluty nie może być pusty");
    }
}
