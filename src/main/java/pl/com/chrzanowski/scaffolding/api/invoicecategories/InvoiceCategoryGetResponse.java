package pl.com.chrzanowski.scaffolding.api.invoicecategories;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class InvoiceCategoryGetResponse extends CoreFieldsResponseRequestData {

    public InvoiceCategoryGetResponse(Long id,
                                      String name,
                                      String createDate,
                                      String modifyDate,
                                      String removeDate,
                                      Long createUserId,
                                      Long modifyUserId,
                                      Long removeUserId,
                                      String createUserName,
                                      String modifyUserName,
                                      String removeUserName) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId, createUserName, modifyUserName, removeUserName);
    }
}


