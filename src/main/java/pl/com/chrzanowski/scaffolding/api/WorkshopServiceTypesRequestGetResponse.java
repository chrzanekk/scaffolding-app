package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class WorkshopServiceTypesRequestGetResponse {

    private List<WorkshopServiceTypesGetResponse> workshopActions;

    public WorkshopServiceTypesRequestGetResponse(List<WorkshopServiceTypesGetResponse> workshopActions) {
        this.workshopActions = workshopActions;
    }

    public List<WorkshopServiceTypesGetResponse> getWorkshopActions() {
        return workshopActions;
    }
}
