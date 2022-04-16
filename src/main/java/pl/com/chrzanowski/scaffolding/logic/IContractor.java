package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorFilter;

import java.util.List;

public interface IContractor {

    Long create(ContractorData data);
    void update(ContractorData data);
    void remove(ContractorData data);
    List<ContractorData> find(ContractorFilter filter);
}
