package pl.com.chrzanowski.scaffolding.api;

public class ServiceWorkshopRequestGetResponse {

    ServiceWorkshopsGetResponse serviceWorkshopsGetResponse;

    public ServiceWorkshopRequestGetResponse(ServiceWorkshopsGetResponse serviceWorkshopsGetResponse) {
        this.serviceWorkshopsGetResponse = serviceWorkshopsGetResponse;
    }

    public ServiceWorkshopsGetResponse getServiceWorkshopsGetResponse() {
        return serviceWorkshopsGetResponse;
    }
}
