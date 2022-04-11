package pl.com.chrzanowski.scaffolding.api.tires;

public class VehicleTireRequestGetResponse {

    private VehicleTiresGetResponse tire;

    public VehicleTireRequestGetResponse(VehicleTiresGetResponse tire) {
        this.tire = tire;
    }

    public VehicleTiresGetResponse getTire() {
        return tire;
    }
}
