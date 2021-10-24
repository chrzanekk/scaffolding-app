package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.VehicleTiresData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTiresFilter;

import java.util.List;

public interface IVehicleTires {

    List<VehicleTiresData> find(VehicleTiresFilter filter);
    VehicleTiresData findById(VehicleTiresFilter filter);
    void create(VehicleTiresData data);
}
