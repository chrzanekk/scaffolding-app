package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class VehiclesBrandRequestGetResponse {

    private List<VehicleBrandGetResponse> brands;

    public VehiclesBrandRequestGetResponse(List<VehicleBrandGetResponse> brands) {
        this.brands = brands;
    }

    public List<VehicleBrandGetResponse> getBrands() {
        return brands;
    }
}
