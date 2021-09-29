package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffServiceActionTypeRequestGetResponse {

    private ScaffServiceActionTypesGetResponse serviceActionType;

    public ScaffServiceActionTypeRequestGetResponse(ScaffServiceActionTypesGetResponse serviceActionType) {
        this.serviceActionType = serviceActionType;
    }

    public ScaffServiceActionTypesGetResponse getServiceActionType() {
        return serviceActionType;
    }
}
