package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.VehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.VehicleTypeFilter;

import java.util.List;

public interface IVehicleType {

    List<VehicleTypeData> find(VehicleTypeFilter filter);
}
