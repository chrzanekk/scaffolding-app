package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffVehiclesModelRequestGetResponse {

    private List<ScaffVehiclesModelGetResponse> models;

    public ScaffVehiclesModelRequestGetResponse(List<ScaffVehiclesModelGetResponse> models) {
        this.models = models;
    }

    public List<ScaffVehiclesModelGetResponse> getModels() {
        return models;
    }
}
