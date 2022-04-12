package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.fueltypes.FuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.fueltypes.FuelTypeFilter;

import java.util.List;

public interface IFuelTypes {
    List<FuelTypeData> find(FuelTypeFilter filter);
}
