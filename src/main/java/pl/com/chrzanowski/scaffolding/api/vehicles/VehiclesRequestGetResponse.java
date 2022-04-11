package pl.com.chrzanowski.scaffolding.api.vehicles;

import java.util.List;

public class VehiclesRequestGetResponse {

    private List<VehicleGetResponse> vehicles;

    public VehiclesRequestGetResponse(List<VehicleGetResponse> vehicles) {
        this.vehicles = vehicles;
    }

    public List<VehicleGetResponse> getVehicles() {
        return vehicles;
    }
}
