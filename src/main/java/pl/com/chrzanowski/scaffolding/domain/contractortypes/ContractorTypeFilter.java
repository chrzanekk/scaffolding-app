package pl.com.chrzanowski.scaffolding.domain.contractortypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsFilter;

public class ContractorTypeFilter extends CoreFieldsFilter {

    public ContractorTypeFilter(Long page,
                                Long pageSize,
                                Boolean isRemoved) {
        super(page, pageSize, isRemoved);
    }

    public ContractorTypeFilter(Long id,
                                Boolean isRemoved) {
        super(id, isRemoved);
    }
}
