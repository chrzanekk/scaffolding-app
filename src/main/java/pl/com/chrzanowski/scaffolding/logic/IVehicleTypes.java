package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.vehicletypes.VehicleTypeData;
import pl.com.chrzanowski.scaffolding.domain.vehicletypes.VehicleTypeFilter;

import java.util.List;

public interface IVehicleTypes {

    List<VehicleTypeData> find(VehicleTypeFilter filter);
}
