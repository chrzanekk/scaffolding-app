package pl.com.chrzanowski.scaffolding.api.invoicecategories;

import java.util.List;

public class InvoiceCategoriesRequestGetResponse {

    private List<InvoiceCategoryGetResponse> contractorTypes;

    public InvoiceCategoriesRequestGetResponse(List<InvoiceCategoryGetResponse> contractorTypes) {
        this.contractorTypes = contractorTypes;
    }

    public List<InvoiceCategoryGetResponse> getContractorTypes() {
        return contractorTypes;
    }
}
