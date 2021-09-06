package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffVehiclesBrandRequestGetResponse {

    private List<ScaffVehiclesBrandGetResponse> brands;

    public ScaffVehiclesBrandRequestGetResponse(List<ScaffVehiclesBrandGetResponse> brands) {
        this.brands = brands;
    }

    public List<ScaffVehiclesBrandGetResponse> getBrands() {
        return brands;
    }
}
