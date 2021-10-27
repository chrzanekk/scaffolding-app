package pl.com.chrzanowski.scaffolding.api;

public class WorkshopRequestGetResponse {

    WorkshopsGetResponse workshopsGetResponse;

    public WorkshopRequestGetResponse(WorkshopsGetResponse workshopsGetResponse) {
        this.workshopsGetResponse = workshopsGetResponse;
    }

    public WorkshopsGetResponse getServiceWorkshopsGetResponse() {
        return workshopsGetResponse;
    }
}
