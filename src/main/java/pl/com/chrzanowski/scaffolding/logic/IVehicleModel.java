package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.VehicleModelData;
import pl.com.chrzanowski.scaffolding.domain.VehicleModelFilter;

import java.util.List;

public interface IVehicleModel {
    Long add(VehicleModelData filter);
    List<VehicleModelData> find(VehicleModelFilter filter);
}
