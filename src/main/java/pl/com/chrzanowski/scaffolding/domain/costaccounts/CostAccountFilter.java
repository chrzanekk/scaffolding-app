package pl.com.chrzanowski.scaffolding.domain.costaccounts;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsFilter;

public class CostAccountFilter extends CoreFieldsFilter {
    public CostAccountFilter(Long page, Long pageSize, Boolean isRemoved) {
        super(page, pageSize, isRemoved);
    }

    public CostAccountFilter(Long id, Boolean isRemoved) {
        super(id, isRemoved);
    }

    public CostAccountFilter(Long id) {
        super(id);
    }
}
