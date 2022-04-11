package pl.com.chrzanowski.scaffolding.api.serviceactiontypes;

public class ServiceActionTypeRequestGetResponse {

    private ServiceActionTypesGetResponse serviceActionType;

    public ServiceActionTypeRequestGetResponse(ServiceActionTypesGetResponse serviceActionType) {
        this.serviceActionType = serviceActionType;
    }

    public ServiceActionTypesGetResponse getServiceActionType() {
        return serviceActionType;
    }
}
