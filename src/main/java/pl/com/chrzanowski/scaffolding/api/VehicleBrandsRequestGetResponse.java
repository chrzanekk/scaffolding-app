package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class VehicleBrandsRequestGetResponse {

    private List<VehicleBrandGetResponse> brands;

    public VehicleBrandsRequestGetResponse(List<VehicleBrandGetResponse> brands) {
        this.brands = brands;
    }

    public List<VehicleBrandGetResponse> getBrands() {
        return brands;
    }
}
