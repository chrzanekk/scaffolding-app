package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;

import java.util.List;

public interface IVehicleBrand {
    List<VehicleBrandData> find(VehicleBrandFilter filter);
}
