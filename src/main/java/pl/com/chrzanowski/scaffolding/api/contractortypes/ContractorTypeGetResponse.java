package pl.com.chrzanowski.scaffolding.api.contractortypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class ContractorTypeGetResponse extends CoreFieldsResponseRequestData {

    public ContractorTypeGetResponse(Long id,
                                     String name,
                                     String createDate,
                                     String modifyDate,
                                     String removeDate,
                                     Long createUserId,
                                     Long modifyUserId,
                                     Long removeUserId) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
    }
}


