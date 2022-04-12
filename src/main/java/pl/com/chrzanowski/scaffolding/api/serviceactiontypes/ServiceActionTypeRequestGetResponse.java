package pl.com.chrzanowski.scaffolding.api.serviceactiontypes;

public class ServiceActionTypeRequestGetResponse {

    private ServiceActionTypeGetResponse serviceActionType;

    public ServiceActionTypeRequestGetResponse(ServiceActionTypeGetResponse serviceActionType) {
        this.serviceActionType = serviceActionType;
    }

    public ServiceActionTypeGetResponse getServiceActionType() {
        return serviceActionType;
    }
}
