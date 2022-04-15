package pl.com.chrzanowski.scaffolding.api.contractortypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class ContractorTypePutRequest extends CoreFieldsResponseRequestData {
    public ContractorTypePutRequest(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }
}
