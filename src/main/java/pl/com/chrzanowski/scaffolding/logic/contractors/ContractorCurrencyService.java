package pl.com.chrzanowski.scaffolding.logic.contractors;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorCurrencyData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorCurrencyFilter;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.getString;

@Service
public class ContractorCurrencyService {

    private ContractorCurrencyJdbcRepository contractorCurrencyJdbcRepository;

    public ContractorCurrencyService(ContractorCurrencyJdbcRepository contractorCurrencyJdbcRepository) {
        this.contractorCurrencyJdbcRepository = contractorCurrencyJdbcRepository;
    }

    public void deleteCurrency(ContractorData data) {
        contractorCurrencyJdbcRepository.deleteCurrency(new ContractorCurrencyFilter(data.getId()));
    }

    public void validateAndCreateCurrencyList(ContractorData data) {
        if (data.getCurrencyList() != null) {
            contractorCurrencyJdbcRepository.create(new ContractorCurrencyData(data.getId(), data.getCurrencyList()));
        } else {
            throw new IllegalArgumentException("Waluty nie mogą być puste.");
        }
    }

    public ContractorCurrencyData get(Long id) {
        return find(new ContractorCurrencyFilter(id)).get(0);
    }

    public List<ContractorCurrencyData> find(ContractorCurrencyFilter filter) {
        return getContractorsCurrencyList(contractorCurrencyJdbcRepository.find(filter));
    }

    public Long[] getCurrencyListForContractors(ContractorData data) {
        List<ContractorCurrencyData> contractorCurrencyData = find(new ContractorCurrencyFilter(data.getId()));
        if (contractorCurrencyData != null) {
            return getCurrencyList(contractorCurrencyData);
        } else {
            throw new IllegalArgumentException("Waluty nie moga byc puste.");
        }
    }


    private Long[] getCurrencyList(List<ContractorCurrencyData> contractorCurrencyData) {
        Long[] currencyList = new Long[contractorCurrencyData.size()];
        int i = 0;
        for (ContractorCurrencyData data : contractorCurrencyData) {
            currencyList[i] = data.getCurrencyId();
            i++;
        }
        return currencyList;
    }

    private List<ContractorCurrencyData> getContractorsCurrencyList(List<Map<String, Object>> data) {
        List<ContractorCurrencyData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new ContractorCurrencyData(
                    getLong(row, "id"),
                    getLong(row, "contractor_id"),
                    getLong(row, "currency_id"),
                    getString(row, "name"),
                    getString(row, "code")
            ));
        }
        return list;
    }
}
