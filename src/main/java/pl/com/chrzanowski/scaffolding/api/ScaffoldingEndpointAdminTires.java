package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.tires.*;
import pl.com.chrzanowski.scaffolding.api.tireseasons.TireSeasonGetResponse;
import pl.com.chrzanowski.scaffolding.api.tireseasons.TireSeasonPostRequest;
import pl.com.chrzanowski.scaffolding.api.tireseasons.TireSeasonPutRequest;
import pl.com.chrzanowski.scaffolding.api.tireseasons.TireSeasonsRequestGetResponse;
import pl.com.chrzanowski.scaffolding.domain.TireSeasonData;
import pl.com.chrzanowski.scaffolding.domain.TireSeasonFilter;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;
import pl.com.chrzanowski.scaffolding.logic.dictionaries.DictionariesService;
import pl.com.chrzanowski.scaffolding.logic.ITireSeasons;
import pl.com.chrzanowski.scaffolding.logic.IVehicleTires;
import pl.com.chrzanowski.scaffolding.logic.IVehicles;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminTires {
    private IVehicles vehicles;
    private ITireSeasons tireSeason;
    private IVehicleTires vehicleTires;
    private DictionariesService dictionariesService;

    public ScaffoldingEndpointAdminTires(IVehicles vehicles, ITireSeasons tireSeason, IVehicleTires vehicleTires, DictionariesService dictionariesService) {
        this.vehicles = vehicles;
        this.tireSeason = tireSeason;
        this.vehicleTires = vehicleTires;
        this.dictionariesService = dictionariesService;
    }

    @GetMapping(path = "/vehicles/{id}/tires", produces = "application/json; charset=UTF-8")
    public VehicleTiresRequestGetResponse tiresByVehicleId(@PathVariable Long id,
                                                           @RequestParam(name = "status", required = false) String status,
                                                           @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                           @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<VehicleTiresData> tires = vehicleTires.find(new VehicleTiresFilter(id, status, page, pageSize));
        return new VehicleTiresRequestGetResponse(tiresToResponse(tires));
    }

    @GetMapping(path = "/vehicles/{vehicleId}/tires/{tireId}", produces = "application/json; charset=UTF-8")
    public VehicleTireRequestGetResponse tireById(@PathVariable Long vehicleId,
                                                  @PathVariable Long tireId) {
        VehicleTiresData tire = vehicleTires.getTire(new VehicleTiresFilter(tireId, vehicleId));
        return new VehicleTireRequestGetResponse(tireToResponse(tire));
    }

    @PostMapping(path = "/vehicles/{id}/tires", consumes = "application/json; charset=UTF-8")
    public void addTires(
            @PathVariable Long id,
            @RequestBody VehicleTiresPostRequest request) {
        vehicleTires.create(new VehicleTiresData(
                id,
                request.getBrand(),
                request.getModel(),
                request.getProductionYear(),
                request.getPurchaseDate(),
                request.getWidth(),
                request.getProfile(),
                request.getDiameter(),
                request.getType(),
                request.getSpeedIndex(),
                request.getCapacityIndex(),
                request.getReinforced(),
                request.getRunOnFlat(),
                request.getSeasonId(),
                request.getStatus()
        ));
    }

    @PutMapping(path = "/vehicles/{vehicleId}/tires/{tireId}", consumes = "application/json; charset=UTF-8")
    public void updateTires(@PathVariable Long tireId,
                            @PathVariable Long vehicleId,
                            @RequestBody VehicleTiresPutRequest request) {
        vehicleTires.update(new VehicleTiresData(
                tireId,
                vehicleId,
                request.getTireId(),
                request.getStatus(),
                request.getProductionYear(),
                request.getPurchaseDate(),
                request.getBrand(),
                request.getModel(),
                request.getWidth(),
                request.getProfile(),
                request.getDiameter(),
                request.getType(),
                request.getSpeedIndex(),
                request.getCapacityIndex(),
                request.getReinforced(),
                request.getRunOnFlat(),
                request.getSeasonId()
        ));
    }


    @GetMapping(path = "/tire-seasons", produces = "application/json; charset=UTF-8")
    public TireSeasonsRequestGetResponse tireSeasons(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                     @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<TireSeasonData> tireSeasons = tireSeason.find(new TireSeasonFilter(page, pageSize));
        return new TireSeasonsRequestGetResponse(tireSeasonsToResponse(tireSeasons));

    }

    @PostMapping(path = "/tire-season", consumes = "application/json; charset=UTF-8")
    public void addTireSeason(@RequestBody TireSeasonPostRequest request) {
        tireSeason.add(new TireSeasonData(request.getName()));
    }

    @PutMapping(path = "/tire-season/{id}", consumes = "application/json; charset=UTF-8")
    public void updateTireSeason(@PathVariable Long id,
                                 @RequestBody TireSeasonPutRequest request) {
        tireSeason.update(new TireSeasonData(id, request.getName()));
    }


    private List<TireSeasonGetResponse> tireSeasonsToResponse(List<TireSeasonData> tireSeasons) {
        List<TireSeasonGetResponse> list = new ArrayList<>();
        for (TireSeasonData data : tireSeasons) {
            list.add(new TireSeasonGetResponse(
                    data.getId(),
                    data.getName()
            ));
        }
        return list;
    }

    private List<VehicleTiresGetResponse> tiresToResponse(List<VehicleTiresData> tires) {
        List<VehicleTiresGetResponse> list = new ArrayList<>();
        for (VehicleTiresData data : tires) {
            list.add(new VehicleTiresGetResponse(
                    data.getId(),
                    data.getVehicleId(),
                    data.getTireId(),
                    data.getStatus(),
                    data.getProductionYear(),
                    data.getPurchaseDate(),
                    data.getBrand(),
                    data.getModel(),
                    data.getWidth(),
                    data.getProfile(),
                    data.getDiameter(),
                    data.getType(),
                    data.getSpeedIndex(),
                    data.getCapacityIndex(),
                    data.getReinforced(),
                    data.getRunOnFlat(),
                    data.getSeasonId(),
                    data.getSeasonName()
            ));
        }
        return list;
    }

    private VehicleTiresGetResponse tireToResponse(VehicleTiresData data) {
        return new VehicleTiresGetResponse(
                data.getId(),
                data.getVehicleId(),
                data.getTireId(),
                data.getStatus(),
                data.getProductionYear(),
                data.getPurchaseDate(),
                data.getBrand(),
                data.getModel(),
                data.getWidth(),
                data.getProfile(),
                data.getDiameter(),
                data.getType(),
                data.getSpeedIndex(),
                data.getCapacityIndex(),
                data.getReinforced(),
                data.getRunOnFlat(),
                data.getSeasonId(),
                data.getSeasonName()
        );
    }
}
