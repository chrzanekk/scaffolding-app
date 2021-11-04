package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class WorkshopServiceActionsRequestGetResponse {

    private List<WorkshopServiceActionsGetResponse> workshopActions;

    public WorkshopServiceActionsRequestGetResponse(List<WorkshopServiceActionsGetResponse> workshopActions) {
        this.workshopActions = workshopActions;
    }

    public List<WorkshopServiceActionsGetResponse> getWorkshopActions() {
        return workshopActions;
    }
}
