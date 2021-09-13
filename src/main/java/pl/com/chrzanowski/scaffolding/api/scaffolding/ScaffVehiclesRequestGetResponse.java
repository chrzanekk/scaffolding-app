package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffVehiclesRequestGetResponse {

    private List<ScaffVehicleGetResponse> vehicles;

    public ScaffVehiclesRequestGetResponse(List<ScaffVehicleGetResponse> vehicles) {
        this.vehicles = vehicles;
    }

    public List<ScaffVehicleGetResponse> getVehicles() {
        return vehicles;
    }
}
