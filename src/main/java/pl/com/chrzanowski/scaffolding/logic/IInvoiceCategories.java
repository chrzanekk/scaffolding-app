package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.invoicecategories.InvoiceCategoryData;
import pl.com.chrzanowski.scaffolding.domain.invoicecategories.InvoiceCategoryFilter;

import java.util.List;

public interface IInvoiceCategories {

    Long add(InvoiceCategoryData data);
    void update(InvoiceCategoryData data);
    List<InvoiceCategoryData> find(InvoiceCategoryFilter filter);
    void remove(InvoiceCategoryData data);
}
