package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;

import java.util.List;

public interface IVehicle {
    List<VehicleData> find(VehicleFilter filter);
    VehicleData findById(VehicleFilter filter);
    Long add(VehicleData data);
    void update(VehicleData data);
}
