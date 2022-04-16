package pl.com.chrzanowski.scaffolding.domain.invoicecategories;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsFilter;

public class InvoiceCategoryFilter extends CoreFieldsFilter {

    public InvoiceCategoryFilter(Long page,
                                 Long pageSize,
                                 Boolean isRemoved) {
        super(page, pageSize, isRemoved);
    }

    public InvoiceCategoryFilter(Long id,
                                 Boolean isRemoved) {
        super(id, isRemoved);
    }
}
