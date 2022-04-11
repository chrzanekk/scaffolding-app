package pl.com.chrzanowski.scaffolding.api;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.vehicles.*;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;
import pl.com.chrzanowski.scaffolding.logic.IVehicles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminVehicles {
    private IVehicles vehicles;

    public ScaffoldingEndpointAdminVehicles(IVehicles vehicles) {
        this.vehicles = vehicles;
    }

    @GetMapping(path = "/vehicles", produces = "application/json; charset=UTF-8")
    public VehiclesRequestGetResponse vehicles(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "brand_id", required = false) Long brandId,
            @RequestParam(name = "model_id", required = false) Long modelId,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber) throws SQLException {
        List<VehicleData> vehiclesList = vehicles.find(new VehicleFilter(
                registrationNumber,
                brandId,
                modelId,
                page,
                pageSize));
        return new VehiclesRequestGetResponse(vehiclesToResponse(vehiclesList));
    }

    @GetMapping(path = "/vehicles-without-tires", produces = "application/json; charset=UTF-8")
    public VehiclesRequestGetResponse vehiclesWithoutTires() {
        List<VehicleData> vehicleList = vehicles.findWithoutTires(new VehicleFilter());
        return new VehiclesRequestGetResponse(vehiclesWithoutTiresToResponse(vehicleList));
    }

    @GetMapping(path = "/vehicle/{id}", produces = "application/json; charset=UTF-8")
    public VehicleRequestGetResponse vehicleById(
            @PathVariable Long id) {
        VehicleData vehicle = vehicles.findById(new VehicleFilter(id));
        return new VehicleRequestGetResponse(vehicleToResponse(vehicle));
    }

    @GetMapping(path = "/vehicle-tires-check/{id}", produces = "application/json; charset=UTF-8")
    public VehicleRequestGetResponse vehicleTiresCheck(
            @PathVariable Long id) {
        VehicleData vehicle = vehicles.findByIdAndCheckTires(new VehicleFilter(id));
        return new VehicleRequestGetResponse(vehicleToResponse(vehicle));
    }

    @PostMapping(path = "/vehicle", consumes = "application/json; charset=UTF-8")
    public void addVehicle(@RequestBody VehiclePostRequest request) {
        vehicles.add(new VehicleData(
                request.getBrandId(),
                request.getModelId(),
                request.getRegistrationNumber(),
                request.getVin(),
                request.getProductionYear(),
                request.getFirstRegistrationDate(),
                request.getFreePlacesForTechnicalInspections(),
                request.getFuelTypeId(),
                request.getVehicleTypeId(),
                request.getLength(),
                request.getWidth(),
                request.getHeight()));
    }

    @PutMapping(path = "/vehicle/{id}")
    public void updateVehicle(@PathVariable Long id, @RequestBody VehiclePutRequest request) {
        vehicles.update(new VehicleData(
                id,
                request.getBrandName(),
                request.getModelName(),
                request.getRegistrationNumber(),
                request.getVin(),
                request.getProductionYear(),
                request.getFirstRegistrationDate(),
                request.getFreePlacesForTechnicalInspections(),
                request.getFuelTypeId(),
                request.getVehicleTypeId(),
                request.getLength(),
                request.getWidth(),
                request.getHeight())
        );
    }

    private List<VehicleGetResponse> vehiclesToResponse(List<VehicleData> vehicles) {
        List<VehicleGetResponse> list = new ArrayList<>();
        for (VehicleData vehicle : vehicles) {
            list.add(new VehicleGetResponse(
                    vehicle.getId(),
                    vehicle.getBrandName(),
                    vehicle.getModelName(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getVin(),
                    vehicle.getProductionYear(),
                    vehicle.getFirstRegistrationDate(),
                    vehicle.getFreePlacesForTechnicalInspections(),
                    vehicle.getFuelType(),
                    vehicle.getVehicleType(),
                    vehicle.getLength(),
                    vehicle.getWidth(),
                    vehicle.getHeight())
            );
        }
        return list;
    }

    private List<VehicleGetResponse> vehiclesWithoutTiresToResponse(List<VehicleData> vehicles) {
        List<VehicleGetResponse> list = new ArrayList<>();
        for (VehicleData vehicle : vehicles) {
            list.add(new VehicleGetResponse(
                    vehicle.getId(),
                    vehicle.getBrandName(),
                    vehicle.getModelName(),
                    vehicle.getRegistrationNumber()
            ));
        }
        return list;
    }

    private VehicleGetResponse vehicleToResponse(VehicleData vehicleData) {
        return new VehicleGetResponse(
                vehicleData.getId(),
                vehicleData.getBrandName(),
                vehicleData.getModelName(),
                vehicleData.getRegistrationNumber(),
                vehicleData.getVin(),
                vehicleData.getProductionYear(),
                vehicleData.getFirstRegistrationDate(),
                vehicleData.getFreePlacesForTechnicalInspections(),
                vehicleData.getFuelType(),
                vehicleData.getVehicleType(),
                vehicleData.getLength(),
                vehicleData.getWidth(),
                vehicleData.getHeight());
    }


}
