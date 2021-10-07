package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffFuelTypeFilter;

import java.util.List;

public interface IFuelType {
    List<ScaffFuelTypeData> find(ScaffFuelTypeFilter filter);
}
