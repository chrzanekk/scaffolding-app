package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffVehicleRequestGetResponse {

    private List<ScaffVehicleGetResponse> vehicles;

    public ScaffVehicleRequestGetResponse(List<ScaffVehicleGetResponse> vehicles) {
        this.vehicles = vehicles;
    }

    public List<ScaffVehicleGetResponse> getVehicles() {
        return vehicles;
    }
}
