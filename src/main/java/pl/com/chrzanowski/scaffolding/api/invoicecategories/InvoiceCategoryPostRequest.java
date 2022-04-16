package pl.com.chrzanowski.scaffolding.api.invoicecategories;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class InvoiceCategoryPostRequest extends CoreFieldsResponseRequestData {

    public InvoiceCategoryPostRequest(String name, Long createUserId) {
        super(name, createUserId);
    }

}
