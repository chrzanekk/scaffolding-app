package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffServiceActionsRequestGetResponse {

    private List<ScaffServiceActionGetResponse> scaffServiceActionGetResponseList;

    public ScaffServiceActionsRequestGetResponse(List<ScaffServiceActionGetResponse> scaffServiceActionGetResponseList) {
        this.scaffServiceActionGetResponseList = scaffServiceActionGetResponseList;
    }

    public List<ScaffServiceActionGetResponse> getScaffServiceActionGetResponseList() {
        return scaffServiceActionGetResponseList;
    }
}
