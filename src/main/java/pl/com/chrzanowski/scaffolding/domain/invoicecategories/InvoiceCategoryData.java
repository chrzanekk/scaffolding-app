package pl.com.chrzanowski.scaffolding.domain.invoicecategories;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

public class InvoiceCategoryData extends CoreFieldsData {
    public InvoiceCategoryData(String name, Long createUserId) {
        super(name, createUserId);
    }

    public InvoiceCategoryData(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }

    public InvoiceCategoryData(Long id, Long removeUserId, String name) {
        super(id, removeUserId, name);
    }

    public InvoiceCategoryData(Long id,
                               String name,
                               LocalDateTime createDate,
                               LocalDateTime modifyDate,
                               LocalDateTime removeDate,
                               Long createUserId,
                               Long modifyUserId,
                               Long removeUserId) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
    }

}
