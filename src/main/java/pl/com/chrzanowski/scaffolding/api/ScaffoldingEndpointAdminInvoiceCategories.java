package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.invoicecategories.*;
import pl.com.chrzanowski.scaffolding.domain.invoicecategories.InvoiceCategoryData;
import pl.com.chrzanowski.scaffolding.domain.invoicecategories.InvoiceCategoryFilter;
import pl.com.chrzanowski.scaffolding.logic.IInvoiceCategories;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminInvoiceCategories {

    private IInvoiceCategories invoiceCategoriesService;

    public ScaffoldingEndpointAdminInvoiceCategories(IInvoiceCategories invoiceCategoriesService) {
        this.invoiceCategoriesService = invoiceCategoriesService;
    }

    @GetMapping(path = "/invoice-categories", produces = "application/json; charset=UTF-8")
    public InvoiceCategoriesRequestGetResponse contractorTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<InvoiceCategoryData> invoiceCategoryDataList = invoiceCategoriesService.find(new InvoiceCategoryFilter(page, pageSize,
                false));
        return new InvoiceCategoriesRequestGetResponse(invoiceCategoriesToResponse(invoiceCategoryDataList));
    }

    @GetMapping(path = "/removed-invoice-categories", produces = "application/json; charset=UTF-8")
    public InvoiceCategoriesRequestGetResponse removedContractorTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<InvoiceCategoryData> contractorTypesList = invoiceCategoriesService.find(new InvoiceCategoryFilter(page, pageSize,
                true));
        return new InvoiceCategoriesRequestGetResponse(invoiceCategoriesToResponse(contractorTypesList));
    }

    @GetMapping(path = "/invoice-category/{id}", produces = "application/json; charset=UTF-8")
    public InvoiceCategoryRequestGetResponse invoiceCategoryById(
            @PathVariable Long id) {
        InvoiceCategoryData contractorType = invoiceCategoriesService.find(new InvoiceCategoryFilter(id, false)).get(0);
        return new InvoiceCategoryRequestGetResponse(invoiceCategoryToResponse(contractorType));
    }

    @PostMapping(path = "/invoice-category", consumes = "application/json; charset=UTF-8")
    public void addInvoiceCategory(@RequestBody InvoiceCategoryPostRequest request) {
        invoiceCategoriesService.add(new InvoiceCategoryData(request.getName(), request.getCreateUserId()));
    }

    @PutMapping(path = "/invoice-category/{id}", consumes = "application/json; charset=UTF-8")
    public void updateInvoiceCategory(@PathVariable Long id,
                                     @RequestBody InvoiceCategoryPutRequest request) {
        invoiceCategoriesService.update(new InvoiceCategoryData(id, request.getName(), request.getModifyUserId()));
    }

    @PutMapping(path = "/invoice-category-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeInvoiceCategory(@PathVariable Long id,
                                     @RequestBody InvoiceCategoryPutRequest request) {
        invoiceCategoriesService.remove(new InvoiceCategoryData(id, request.getRemoveUserId(), request.getName()));
    }


    private List<InvoiceCategoryGetResponse> invoiceCategoriesToResponse(List<InvoiceCategoryData> invoiceCategory) {
        List<InvoiceCategoryGetResponse> list = new ArrayList<>();
        for (InvoiceCategoryData data : invoiceCategory) {
            list.add(invoiceCategoryToResponse(data));
        }
        return list;
    }

    private InvoiceCategoryGetResponse invoiceCategoryToResponse(InvoiceCategoryData invoiceCategory) {
        return new InvoiceCategoryGetResponse(
                invoiceCategory.getId(),
                invoiceCategory.getName(),
                DateUtil.parseDateTime(invoiceCategory.getCreateDate()),
                DateUtil.parseDateTime(invoiceCategory.getModifyDate()),
                DateUtil.parseDateTime(invoiceCategory.getRemoveDate()),
                invoiceCategory.getCreateUserId(),
                invoiceCategory.getModifyUserId(),
                invoiceCategory.getRemoveUserId()
        );
    }
}
