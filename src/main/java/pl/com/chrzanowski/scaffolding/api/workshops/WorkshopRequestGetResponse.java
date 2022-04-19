package pl.com.chrzanowski.scaffolding.api.workshops;

public class WorkshopRequestGetResponse {

    private WorkshopGetResponse workshop;

    public WorkshopRequestGetResponse(WorkshopGetResponse workshop) {
        this.workshop = workshop;
    }

    public WorkshopGetResponse getServiceWorkshopsGetResponse() {
        return workshop;
    }
}
