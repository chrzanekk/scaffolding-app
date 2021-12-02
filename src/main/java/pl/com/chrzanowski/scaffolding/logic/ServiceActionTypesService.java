package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsFilter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;

@Service
public class ServiceActionTypesService implements IServiceActonTypes {

    private ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository;
    private ServiceActionsService serviceActionsService;

    public ServiceActionTypesService(ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository,
                                     ServiceActionsService serviceActionsService) {

        this.serviceActionTypeJdbcRepository = serviceActionTypeJdbcRepository;
        this.serviceActionsService = serviceActionsService;
    }

    public List<ServiceActionTypeData> find(ServiceActionTypesFilter filter) {
        return getActionTypes(serviceActionTypeJdbcRepository.find(filter));
    }



    public Long add(ServiceActionTypeData data) {
        validateData(data);
        return serviceActionTypeJdbcRepository.create(data);
    }

    public void update(ServiceActionTypeData data) {
        validateData(data);
        serviceActionTypeJdbcRepository.update(data);
    }

    public void delete(ServiceActionTypeData data) {
        validateData(data);
        if(!data.getId().equals(8L)) {
            updateExistingActionTypesToDefault(data);
            serviceActionTypeJdbcRepository.remove(data);
        }
        else {
            throw new IllegalArgumentException("Nie można usunąć tej pozycji.");
        }
    }


    private List<ServiceActionTypeData> getActionTypes(List<Map<String, Object>> data) {

        List<ServiceActionTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new ServiceActionTypeData(
                    getLong(row, "id"),
                    getString(row, "name")
            ));
        }
        return list;
    }

    private void validateData(ServiceActionTypeData data) {
        DataValidationUtil.validateTextField(data.getName(), "Nazwa usługi serwisowej");
    }
    private void updateExistingActionTypesToDefault(ServiceActionTypeData data) {
        List<ServiceActionsData> findExisting = serviceActionsService.find(new ServiceActionsFilter(null,data.getId()));
        for(ServiceActionsData element : findExisting) {
            serviceActionsService.update(new ServiceActionsData(element, 8L));
        }
    }
}
