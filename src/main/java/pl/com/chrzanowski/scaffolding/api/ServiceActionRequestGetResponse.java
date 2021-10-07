package pl.com.chrzanowski.scaffolding.api;

public class ServiceActionRequestGetResponse {

    private ServiceActionGetResponse serviceActionGetResponse;

    public ServiceActionRequestGetResponse(ServiceActionGetResponse serviceActionGetResponse) {
        this.serviceActionGetResponse = serviceActionGetResponse;
    }

    public ServiceActionGetResponse getServiceActionGetResponse() {
        return serviceActionGetResponse;
    }
}
