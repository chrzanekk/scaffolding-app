package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeData;
import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeFilter;

import java.util.List;

public interface IContractorTypes {

    Long add(ContractorTypeData data);
    void update(ContractorTypeData data);
    List<ContractorTypeData> find(ContractorTypeFilter filter);
    void remove(ContractorTypeData data);
}
