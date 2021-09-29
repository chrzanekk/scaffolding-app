package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffServiceWorkshopsRequestGetResponse {

    private List<ScaffServiceWorkshopsGetResponse> workshops;

    public ScaffServiceWorkshopsRequestGetResponse(List<ScaffServiceWorkshopsGetResponse> workshops) {
        this.workshops = workshops;
    }

    public List<ScaffServiceWorkshopsGetResponse> getWorkshops() {
        return workshops;
    }
}
