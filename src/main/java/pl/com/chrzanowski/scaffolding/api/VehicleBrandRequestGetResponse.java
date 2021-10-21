package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class VehicleBrandRequestGetResponse {

    private VehicleBrandGetResponse brand;

    public VehicleBrandRequestGetResponse(VehicleBrandGetResponse brand) {
        this.brand = brand;
    }

    public VehicleBrandGetResponse getBrand() {
        return brand;
    }
}
