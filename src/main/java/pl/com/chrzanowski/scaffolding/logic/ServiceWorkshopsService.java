package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceWorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceWorkshopsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;

@Service
public class ServiceWorkshopsService {

    private ServiceWorkshopsJdbcRepository serviceWorkshopsJdbcRepository;

    public ServiceWorkshopsService(ServiceWorkshopsJdbcRepository serviceWorkshopsJdbcRepository) {
        this.serviceWorkshopsJdbcRepository = serviceWorkshopsJdbcRepository;
    }

    public List<ServiceWorkshopsData> find(ServiceWorkshopsFilter filter) {
        return getWorkshops(serviceWorkshopsJdbcRepository.find(filter));
    }

    public Long add(ServiceWorkshopsData data) {
        return serviceWorkshopsJdbcRepository.create(data);
    }
    
    public void update(ServiceWorkshopsData data) {
        serviceWorkshopsJdbcRepository.update(data);
    }

    private List<ServiceWorkshopsData> getWorkshops(List<Map<String,Object>> data) {
        List<ServiceWorkshopsData> list = new ArrayList<>();
        for (Map<String,Object> row : data) {
            list.add(new ServiceWorkshopsData(
                    getLong(row,"id"),
                    getString(row,"name"),
                    getString(row,"tax_number"),
                    getString(row,"street"),
                    getString(row,"building_number"),
                    getString(row, "apartment_number"),
                    getString(row,"postal_code"),
                    getString(row,"city")
            ));
        }
        return list;
    }
}
