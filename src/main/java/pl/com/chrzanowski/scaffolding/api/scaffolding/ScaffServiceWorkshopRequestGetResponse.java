package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffServiceWorkshopRequestGetResponse {

    ScaffServiceWorkshopsGetResponse serviceWorkshopsGetResponse;

    public ScaffServiceWorkshopRequestGetResponse(ScaffServiceWorkshopsGetResponse serviceWorkshopsGetResponse) {
        this.serviceWorkshopsGetResponse = serviceWorkshopsGetResponse;
    }

    public ScaffServiceWorkshopsGetResponse getServiceWorkshopsGetResponse() {
        return serviceWorkshopsGetResponse;
    }
}
