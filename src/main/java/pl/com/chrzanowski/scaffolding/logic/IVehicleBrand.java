package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.VehicleBrandFilter;

import java.util.List;

public interface IVehicleBrand {
    Long add(VehicleBrandData data);
    List<VehicleBrandData> find(VehicleBrandFilter filter);
}
