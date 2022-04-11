package pl.com.chrzanowski.scaffolding.api.serviceactions;

public class ServiceActionRequestGetResponse {

    private ServiceActionGetResponse serviceAction;

    public ServiceActionRequestGetResponse(ServiceActionGetResponse serviceAction) {
        this.serviceAction = serviceAction;
    }

    public ServiceActionGetResponse getServiceAction() {
        return serviceAction;
    }
}
