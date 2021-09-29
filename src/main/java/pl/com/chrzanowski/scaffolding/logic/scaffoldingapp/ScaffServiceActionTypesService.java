package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionTypesFilter;


import java.util.List;

@Service
public class ScaffServiceActionTypesService {

    private ScaffServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository;

    public ScaffServiceActionTypesService(ScaffServiceActionTypeJdbcRepository serviceActionTypeJdbcRepository) {
        this.serviceActionTypeJdbcRepository = serviceActionTypeJdbcRepository;
    }

    public List<ScaffServiceActionTypeData> find(ScaffServiceActionTypesFilter filter) {
        return serviceActionTypeJdbcRepository.find(filter);
    }

    public Long add(ScaffServiceActionTypeData data) {
        return serviceActionTypeJdbcRepository.create(data);
    }

    public void update(ScaffServiceActionTypeData data) {
        serviceActionTypeJdbcRepository.update(data);
    }




}
