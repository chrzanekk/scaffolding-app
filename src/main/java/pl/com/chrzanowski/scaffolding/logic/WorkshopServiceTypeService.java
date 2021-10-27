package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;

@Service
public class WorkshopServiceTypeService {

    private WorkshopServiceTypeJdbcRepository workshopServiceTypeJdbcRepository;

    public WorkshopServiceTypeService(WorkshopServiceTypeJdbcRepository workshopServiceTypeJdbcRepository) {
        this.workshopServiceTypeJdbcRepository = workshopServiceTypeJdbcRepository;
    }

    public void deleteActionTypes(WorkshopsData data) {
        workshopServiceTypeJdbcRepository.deleteActionsFromWorkshop(new WorkshopServiceTypeFilter(data.getId()));
    }




}
