package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class VehicleTiresRequestGetResponse {

    private List<VehicleTiresGetResponse> tires;

    public VehicleTiresRequestGetResponse(List<VehicleTiresGetResponse> tires) {
        this.tires = tires;
    }

    public List<VehicleTiresGetResponse> getTires() {
        return tires;
    }
}
