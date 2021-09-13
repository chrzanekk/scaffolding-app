package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffVehicleRequestGetResponse {

    private ScaffVehicleGetResponse vehicleGetResponse;

    public ScaffVehicleRequestGetResponse(ScaffVehicleGetResponse vehicleGetResponse) {
        this.vehicleGetResponse = vehicleGetResponse;
    }

    public ScaffVehicleGetResponse getVehicleGetResponse() {
        return vehicleGetResponse;
    }
}
