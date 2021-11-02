package pl.com.chrzanowski.scaffolding.api;

public class ServiceActionRequestGetResponse {

    private ServiceActionGetResponse serviceAction;

    public ServiceActionRequestGetResponse(ServiceActionGetResponse serviceAction) {
        this.serviceAction = serviceAction;
    }

    public ServiceActionGetResponse getServiceAction() {
        return serviceAction;
    }
}
