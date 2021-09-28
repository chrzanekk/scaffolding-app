package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceWorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceWorkshopsFilter;

import java.util.List;

@Service
public class ScaffServiceWorkshopsService {

    private ScaffServiceWorkshopsJdbcRepository serviceWorkshopsJdbcRepository;

    public ScaffServiceWorkshopsService(ScaffServiceWorkshopsJdbcRepository serviceWorkshopsJdbcRepository) {
        this.serviceWorkshopsJdbcRepository = serviceWorkshopsJdbcRepository;
    }

    public List<ScaffServiceWorkshopsData> find(ScaffServiceWorkshopsFilter filter) {
        return serviceWorkshopsJdbcRepository.find(filter);
    }

    public Long add(ScaffServiceWorkshopsData data) {
        return serviceWorkshopsJdbcRepository.create(data);
    }
    
    public void update(ScaffServiceWorkshopsData data) {
        serviceWorkshopsJdbcRepository.update(data);
    }

}
