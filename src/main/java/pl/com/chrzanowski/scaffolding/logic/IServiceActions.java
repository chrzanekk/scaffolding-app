package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsFilter;

import java.util.List;

public interface IServiceActions {
    List<ServiceActionsData> find(ServiceActionsFilter filter);
    ServiceActionsData findById(ServiceActionsFilter filter);
    Long add(ServiceActionsData data);
    void update(ServiceActionsData data);
    void delete(ServiceActionsData data);

}
