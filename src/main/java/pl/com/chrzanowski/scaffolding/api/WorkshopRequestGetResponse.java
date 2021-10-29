package pl.com.chrzanowski.scaffolding.api;

public class WorkshopRequestGetResponse {

    WorkshopsGetResponse workshop;

    public WorkshopRequestGetResponse(WorkshopsGetResponse workshop) {
        this.workshop = workshop;
    }

    public WorkshopsGetResponse getServiceWorkshopsGetResponse() {
        return workshop;
    }
}
