package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceWorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceWorkshopsFilter;

import java.util.List;

@Service
public class ServiceWorkshopsService {

    private ServiceWorkshopsJdbcRepository serviceWorkshopsJdbcRepository;

    public ServiceWorkshopsService(ServiceWorkshopsJdbcRepository serviceWorkshopsJdbcRepository) {
        this.serviceWorkshopsJdbcRepository = serviceWorkshopsJdbcRepository;
    }

    public List<ServiceWorkshopsData> find(ServiceWorkshopsFilter filter) {
        return serviceWorkshopsJdbcRepository.find(filter);
    }

    public Long add(ServiceWorkshopsData data) {
        return serviceWorkshopsJdbcRepository.create(data);
    }
    
    public void update(ServiceWorkshopsData data) {
        serviceWorkshopsJdbcRepository.update(data);
    }

}
