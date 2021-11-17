package pl.com.chrzanowski.scaffolding.api;

public class VehicleRequestGetResponse {

    private VehicleGetResponse vehicle;

    public VehicleRequestGetResponse(VehicleGetResponse vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleGetResponse getVehicle() {
        return vehicle;
    }
}
