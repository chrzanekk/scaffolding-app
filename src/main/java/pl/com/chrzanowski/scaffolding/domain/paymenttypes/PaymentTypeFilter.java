package pl.com.chrzanowski.scaffolding.domain.paymenttypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsFilter;

public class PaymentTypeFilter extends CoreFieldsFilter{

    public PaymentTypeFilter(Long id,
                             String name,
                             Long page,
                             Long pageSize,
                             Boolean isRemoved) {
        super(id, name, page, pageSize, isRemoved);
    }

    public PaymentTypeFilter(Long page, Long pageSize, Boolean isRemoved) {
        super(page, pageSize, isRemoved);
    }

    public PaymentTypeFilter(Long id) {
        super(id);
    }
}
