package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class VehiclesModelRequestGetResponse {

    private List<VehicleModelGetResponse> models;

    public VehiclesModelRequestGetResponse(List<VehicleModelGetResponse> models) {
        this.models = models;
    }

    public List<VehicleModelGetResponse> getModels() {
        return models;
    }
}
