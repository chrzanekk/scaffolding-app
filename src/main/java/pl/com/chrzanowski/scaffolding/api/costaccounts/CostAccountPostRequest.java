package pl.com.chrzanowski.scaffolding.api.costaccounts;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class CostAccountPostRequest extends CoreFieldsResponseRequestData {

    public CostAccountPostRequest(String name, Long createUserId) {
        super(name, createUserId);
    }
}
