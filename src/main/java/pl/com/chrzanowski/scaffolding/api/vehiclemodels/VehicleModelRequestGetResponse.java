package pl.com.chrzanowski.scaffolding.api.vehiclemodels;

public class VehicleModelRequestGetResponse {

    private VehicleModelGetResponse model;

    public VehicleModelRequestGetResponse(VehicleModelGetResponse model) {
        this.model = model;
    }

    public VehicleModelGetResponse getModel() {
        return model;
    }
}
