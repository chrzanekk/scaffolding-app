package pl.com.chrzanowski.scaffolding.api.invoicecategories;

public class InvoiceCategoryRequestGetResponse {

    private InvoiceCategoryGetResponse contractorType;

    public InvoiceCategoryRequestGetResponse(InvoiceCategoryGetResponse contractorType) {
        this.contractorType = contractorType;
    }

    public InvoiceCategoryGetResponse getContractorType() {
        return contractorType;
    }
}
