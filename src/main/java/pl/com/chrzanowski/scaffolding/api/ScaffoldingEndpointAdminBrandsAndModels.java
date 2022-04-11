package pl.com.chrzanowski.scaffolding.api;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.vehiclebrands.*;
import pl.com.chrzanowski.scaffolding.api.vehiclemodels.*;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;
import pl.com.chrzanowski.scaffolding.logic.IVehicleBrands;
import pl.com.chrzanowski.scaffolding.logic.IVehicleModels;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminBrandsAndModels {

    private IVehicleModels vehicleModels;
    private IVehicleBrands vehicleBrands;

    public ScaffoldingEndpointAdminBrandsAndModels(IVehicleModels vehicleModels, IVehicleBrands vehicleBrands) {
        this.vehicleModels = vehicleModels;
        this.vehicleBrands = vehicleBrands;
    }

    @GetMapping(path = "/brands", produces = "application/json; charset=UTF-8")
    public VehicleBrandsRequestGetResponse brands(
            @RequestParam(name = "brand_name", required = false) String brandName) {
        List<VehicleBrandData> brands = vehicleBrands.find(new VehicleBrandFilter(brandName));
        return new VehicleBrandsRequestGetResponse(brandsToResponse(brands));
    }

    @GetMapping(path = "/brands/{id}", produces = "application/json; charset=UTF-8")
    public VehicleBrandRequestGetResponse brandById(
            @PathVariable Long id) {
        VehicleBrandData brand = vehicleBrands.find(new VehicleBrandFilter(id)).get(0);
        return new VehicleBrandRequestGetResponse(brandToResponse(brand));
    }

    @PostMapping(path = "/brands", consumes = "application/json; charset=UTF-8")
    public void addBrand(@RequestBody VehicleBrandsPostRequest request) {
        vehicleBrands.add(new VehicleBrandData(
                request.getBrandName()
        ));
    }

    @PutMapping(path = "/brands/{id}", consumes = "application/json; charset=UTF-8")
    public void updateBrand(@PathVariable Long id, @RequestBody VehicleBrandsPutRequest request) {
        vehicleBrands.update(new VehicleBrandData(
                id,
                request.getBrandName(),
                LocalDateTime.now()
        ));
    }

    @GetMapping(path = "/brands/{id}/models", produces = "application/json; charset=UTF-8")
    public VehicleModelsRequestGetResponse modelsByBrandId(@PathVariable Long id) {
        List<VehicleModelData> models = vehicleModels.find(new VehicleModelFilter(null, id));
        return new VehicleModelsRequestGetResponse(modelsToResponse(models));
    }

    @GetMapping(path = "/models", produces = "application/json; charset=UTF-8")
    public VehicleModelsRequestGetResponse allModels() {
        List<VehicleModelData> models = vehicleModels.find(new VehicleModelFilter());
        return new VehicleModelsRequestGetResponse(modelsToResponse(models));
    }

    @GetMapping(path = "/brands/{brandId}/models/{modelId}", produces = "application/json; charset=UTF-8")
    public VehicleModelRequestGetResponse modelById(@PathVariable Long brandId,
                                                    @PathVariable Long modelId) {
        VehicleModelData model = vehicleModels.find(new VehicleModelFilter(modelId, brandId)).get(0);
        return new VehicleModelRequestGetResponse(modelToResponse(model));
    }

    @PostMapping(path = "/brands/{id}/models", consumes = "application/json; charset=UTF-8")
    public void addModel(@PathVariable Long id, @RequestBody VehicleModelsPostRequest request) {
        vehicleModels.add(new VehicleModelData(
                request.getModelName(),
                id
        ));
    }

    @PutMapping(path = "/brands/{brandId}/models/{modelId}", consumes = "application/json; charset=UTF-8")
    public void updateModel(@PathVariable Long brandId,
                            @PathVariable Long modelId,
                            @RequestBody VehicleModelsPutRequest request) {
        vehicleModels.update(new VehicleModelData(
                modelId,
                brandId,
                request.getModelName(),
                LocalDateTime.now()
        ));
    }


    private List<VehicleModelGetResponse> modelsToResponse(List<VehicleModelData> models) {
        List<VehicleModelGetResponse> list = new ArrayList<>();
        for (VehicleModelData data : models) {
            list.add(new VehicleModelGetResponse(
                    data.getId(),
                    data.getBrandId(),
                    data.getName()
            ));
        }
        return list;
    }

    private List<VehicleBrandGetResponse> brandsToResponse(List<VehicleBrandData> brands) {
        List<VehicleBrandGetResponse> list = new ArrayList<>();
        for (VehicleBrandData data : brands) {
            list.add(new VehicleBrandGetResponse(
                    data.getId(),
                    data.getName()
            ));
        }
        return list;
    }

    private VehicleBrandGetResponse brandToResponse(VehicleBrandData brand) {
        return new VehicleBrandGetResponse(brand.getId(), brand.getName());
    }

    private VehicleModelGetResponse modelToResponse(VehicleModelData model) {
        return new VehicleModelGetResponse(model.getId(), model.getBrandId(), model.getName());
    }
}
