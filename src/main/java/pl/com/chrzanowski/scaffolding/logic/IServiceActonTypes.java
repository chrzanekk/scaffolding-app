package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypesFilter;

import java.util.List;

public interface IServiceActonTypes {

    Long add(ServiceActionTypeData data);
    void update(ServiceActionTypeData data);
    List<ServiceActionTypeData> find(ServiceActionTypesFilter filter);
}
