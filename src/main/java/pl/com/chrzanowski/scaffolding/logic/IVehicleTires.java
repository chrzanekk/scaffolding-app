package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.tires.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.tires.VehicleTiresFilter;

import java.util.List;

public interface IVehicleTires {

    List<VehicleTiresData> find(VehicleTiresFilter filter);
    VehicleTiresData getTire(VehicleTiresFilter filter);
    void create(VehicleTiresData data);
    void update(VehicleTiresData data);
}
