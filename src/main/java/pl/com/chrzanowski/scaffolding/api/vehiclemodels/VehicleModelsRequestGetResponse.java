package pl.com.chrzanowski.scaffolding.api.vehiclemodels;

import java.util.List;

public class VehicleModelsRequestGetResponse {

    private List<VehicleModelGetResponse> models;

    public VehicleModelsRequestGetResponse(List<VehicleModelGetResponse> models) {
        this.models = models;
    }

    public List<VehicleModelGetResponse> getModels() {
        return models;
    }
}
