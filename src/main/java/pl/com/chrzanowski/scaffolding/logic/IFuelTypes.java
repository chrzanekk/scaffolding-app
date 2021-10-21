package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.FuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.FuelTypeFilter;

import java.util.List;

public interface IFuelTypes {
    List<FuelTypeData> find(FuelTypeFilter filter);
}
