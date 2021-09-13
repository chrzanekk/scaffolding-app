package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffVehiclesModelRequestGetResponse {

    private List<ScaffVehicleModelGetResponse> models;

    public ScaffVehiclesModelRequestGetResponse(List<ScaffVehicleModelGetResponse> models) {
        this.models = models;
    }

    public List<ScaffVehicleModelGetResponse> getModels() {
        return models;
    }
}
