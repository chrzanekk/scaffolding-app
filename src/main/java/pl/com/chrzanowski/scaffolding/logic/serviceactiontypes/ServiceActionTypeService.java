package pl.com.chrzanowski.scaffolding.logic.serviceactiontypes;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypesFilter;
import pl.com.chrzanowski.scaffolding.logic.IServiceActonTypes;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class ServiceActionTypeService implements IServiceActonTypes {

    private ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository;


    public ServiceActionTypeService(ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository) {

        this.serviceActionTypeJdbcRepository = serviceActionTypeJdbcRepository;
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

    private List<ServiceActionTypeData> getActionTypes(List<Map<String, Object>> data) {

        List<ServiceActionTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new ServiceActionTypeData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getDateTime(row,"remove_date")
            ));
        }
        return list;
    }

    private void validateData(ServiceActionTypeData data) {
        DataValidationUtil.validateTextField(data.getName(), "Nazwa usługi serwisowej");

    }

}
