package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class WorkshopsRequestGetResponse {

    private List<WorkshopsGetResponse> workshops;

    public WorkshopsRequestGetResponse(List<WorkshopsGetResponse> workshops) {
        this.workshops = workshops;
    }

    public List<WorkshopsGetResponse> getWorkshops() {
        return workshops;
    }
}
