package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class ServiceWorkshopsRequestGetResponse {

    private List<ServiceWorkshopsGetResponse> workshops;

    public ServiceWorkshopsRequestGetResponse(List<ServiceWorkshopsGetResponse> workshops) {
        this.workshops = workshops;
    }

    public List<ServiceWorkshopsGetResponse> getWorkshops() {
        return workshops;
    }
}
