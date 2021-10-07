package pl.com.chrzanowski.scaffolding.api;

public class VehicleRequestGetResponse {

    private VehicleGetResponse vehicleGetResponse;

    public VehicleRequestGetResponse(VehicleGetResponse vehicleGetResponse) {
        this.vehicleGetResponse = vehicleGetResponse;
    }

    public VehicleGetResponse getVehicleGetResponse() {
        return vehicleGetResponse;
    }
}
