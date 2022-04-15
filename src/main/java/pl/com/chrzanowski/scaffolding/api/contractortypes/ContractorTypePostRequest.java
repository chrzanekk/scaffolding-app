package pl.com.chrzanowski.scaffolding.api.contractortypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class ContractorTypePostRequest extends CoreFieldsResponseRequestData {

    public ContractorTypePostRequest(String name, Long createUserId) {
        super(name, createUserId);
    }

}
