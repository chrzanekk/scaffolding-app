package pl.com.chrzanowski.scaffolding.logic.contractortypes;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeData;
import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.IContractorTypes;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class ContractorTypeService implements IContractorTypes {

    private ContractorTypeJdbcRepository contractorTypeJdbcRepository;

    public ContractorTypeService(ContractorTypeJdbcRepository contractorTypeJdbcRepository) {
        this.contractorTypeJdbcRepository = contractorTypeJdbcRepository;
    }

    @Override
    public Long add(ContractorTypeData data) {
        validateData(data);
        return contractorTypeJdbcRepository.create(data);

    }

    @Override
    public void update(ContractorTypeData data) {
        validateData(data);
        contractorTypeJdbcRepository.update(data);
    }

    public void remove(ContractorTypeData data) {
        contractorTypeJdbcRepository.remove(data);
    }

    @Override
    public List<ContractorTypeData> find(ContractorTypeFilter filter) {
        return getContractorTypes(contractorTypeJdbcRepository.find(filter));
    }

    private List<ContractorTypeData> getContractorTypes(List<Map<String, Object>> data) {
        List<ContractorTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new ContractorTypeData(
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

    private void validateData(ContractorTypeData data) {
        DataValidationUtil.validateTextField(data.getName(), "Typ kontrahenta");
    }
}
