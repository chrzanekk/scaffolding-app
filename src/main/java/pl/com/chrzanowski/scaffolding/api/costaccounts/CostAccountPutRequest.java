package pl.com.chrzanowski.scaffolding.api.costaccounts;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class CostAccountPutRequest extends CoreFieldsResponseRequestData {

    public CostAccountPutRequest(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }
}
