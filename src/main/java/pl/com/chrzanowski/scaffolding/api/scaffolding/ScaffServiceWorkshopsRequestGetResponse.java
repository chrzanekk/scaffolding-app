package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffServiceWorkshopsRequestGetResponse {

    private List<ScaffServiceWorkshopsGetResponse> serviceWorkshopsGetResponse;

    public ScaffServiceWorkshopsRequestGetResponse(List<ScaffServiceWorkshopsGetResponse> serviceWorkshopsGetResponse) {
        this.serviceWorkshopsGetResponse = serviceWorkshopsGetResponse;
    }

    public List<ScaffServiceWorkshopsGetResponse> getServiceWorkshopsGetResponse() {
        return serviceWorkshopsGetResponse;
    }
}
