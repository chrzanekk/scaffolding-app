package pl.com.chrzanowski.scaffolding.api.invoicecategories;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class InvoiceCategoryPutRequest extends CoreFieldsResponseRequestData {
    public InvoiceCategoryPutRequest(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }
}
