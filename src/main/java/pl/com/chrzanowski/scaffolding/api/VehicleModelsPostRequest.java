package pl.com.chrzanowski.scaffolding.api;

public class VehicleModelsPostRequest {

    private String modelName;
    private Long brandId;

    public VehicleModelsPostRequest() {
    }

    public String getModelName() {
        return modelName;
    }

    public Long getBrandId() {
        return brandId;
    }
}
