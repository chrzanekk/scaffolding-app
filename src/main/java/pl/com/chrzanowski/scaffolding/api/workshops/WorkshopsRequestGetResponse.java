package pl.com.chrzanowski.scaffolding.api.workshops;

import java.util.List;

public class WorkshopsRequestGetResponse {

    private List<WorkshopGetResponse> workshops;

    public WorkshopsRequestGetResponse(List<WorkshopGetResponse> workshops) {
        this.workshops = workshops;
    }

    public List<WorkshopGetResponse> getWorkshops() {
        return workshops;
    }
}
