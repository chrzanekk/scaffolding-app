package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandData;
import pl.com.chrzanowski.scaffolding.domain.vehiclebrands.VehicleBrandFilter;

import java.util.List;

public interface IVehicleBrands {
    Long add(VehicleBrandData data);
    void update(VehicleBrandData data);
    List<VehicleBrandData> find(VehicleBrandFilter filter);
}
