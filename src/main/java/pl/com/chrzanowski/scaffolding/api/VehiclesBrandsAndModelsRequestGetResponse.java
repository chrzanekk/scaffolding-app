package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class VehiclesBrandsAndModelsRequestGetResponse {

    private List<VehiclesBrandsAndModelsGetResponse> brandsAndModels;

    public VehiclesBrandsAndModelsRequestGetResponse(List<VehiclesBrandsAndModelsGetResponse> brandsAndModels) {
        this.brandsAndModels = brandsAndModels;
    }

    public List<VehiclesBrandsAndModelsGetResponse> getBrandsAndModels() {
        return brandsAndModels;
    }
}
