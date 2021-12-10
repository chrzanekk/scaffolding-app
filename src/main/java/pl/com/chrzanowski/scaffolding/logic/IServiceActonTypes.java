package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;

import java.util.List;
import java.util.Map;

public interface IServiceActonTypes {

    Long add(ServiceActionTypeData data);
    void update(ServiceActionTypeData data);
    List<ServiceActionTypeData> find(ServiceActionTypesFilter filter);
}
