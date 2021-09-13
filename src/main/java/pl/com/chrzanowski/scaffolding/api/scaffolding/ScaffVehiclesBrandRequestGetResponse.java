package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffVehiclesBrandRequestGetResponse {

    private List<ScaffVehicleBrandGetResponse> brands;

    public ScaffVehiclesBrandRequestGetResponse(List<ScaffVehicleBrandGetResponse> brands) {
        this.brands = brands;
    }

    public List<ScaffVehicleBrandGetResponse> getBrands() {
        return brands;
    }
}
