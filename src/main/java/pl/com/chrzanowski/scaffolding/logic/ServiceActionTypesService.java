package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;


import java.util.List;

@Service
public class ServiceActionTypesService {

    private ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository;

    public ServiceActionTypesService(ServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository) {
        this.serviceActionTypeJdbcRepository = serviceActionTypeJdbcRepository;
    }

    public List<ServiceActionTypeData> find(ServiceActionTypesFilter filter) {
        return serviceActionTypeJdbcRepository.find(filter);
    }

    public Long add(ServiceActionTypeData data) {
        return serviceActionTypeJdbcRepository.create(data);
    }

    public void update(ServiceActionTypeData data) {
        serviceActionTypeJdbcRepository.update(data);
    }




}
