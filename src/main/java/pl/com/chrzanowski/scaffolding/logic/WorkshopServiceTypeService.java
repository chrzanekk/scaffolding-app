package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;

@Service
public class WorkshopServiceTypeService {

    private WorkshopServiceTypeJdbcRepository workshopServiceTypeJdbcRepository;

    public WorkshopServiceTypeService(WorkshopServiceTypeJdbcRepository workshopServiceTypeJdbcRepository) {
        this.workshopServiceTypeJdbcRepository = workshopServiceTypeJdbcRepository;
    }

    public void deleteActionTypes(WorkshopsData data) {
        workshopServiceTypeJdbcRepository.deleteActionsFromWorkshop(new WorkshopServiceTypeFilter(data.getId()));
    }

    public void validateAndCreateActionTypesForWorkshop(WorkshopsData data) {
        if(data.getActionTypes() != null) {
            workshopServiceTypeJdbcRepository.create(new WorkshopServiceTypeData(data.getId(),data.getActionTypes()));
        }
        else {
            throw new IllegalArgumentException("Workshop action types can't be empty");
        }

    }



    public WorkshopServiceTypeData get(Long id) {
        return find(new WorkshopServiceTypeFilter(id)).get(0);
    }

    public List<WorkshopServiceTypeData> find(WorkshopServiceTypeFilter filter) {
        return getWorkshopServices(workshopServiceTypeJdbcRepository.find(filter));
    }

    public Long[] getActionTypesForWorkshop(WorkshopsData data) {
        List<WorkshopServiceTypeData> workshopActionTypesData = find(new WorkshopServiceTypeFilter(data.getId()));
        if(workshopActionTypesData != null) {
            return getActionTypesForWorkshop(workshopActionTypesData);
        } else {
            throw new IllegalArgumentException("Action types can't be empty");
        }
    }

    private Long[] getActionTypesForWorkshop(List<WorkshopServiceTypeData> workshopServiceTypeData) {
        Long[] actionTypes = new Long[workshopServiceTypeData.size()];
        int i = 0;
        for (WorkshopServiceTypeData data : workshopServiceTypeData) {
            actionTypes[i] = data.getServiceActionTypeId();
            i++;
        }
        return actionTypes;
    }


    private List<WorkshopServiceTypeData> getWorkshopServices(List<Map<String, Object>> data) {
        List<WorkshopServiceTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new WorkshopServiceTypeData(
                    getLong(row, "id"),
                    getLong(row, "workshop_id"),
                    getLong(row, "service_action_type_id")
            ));
        }
        return list;
    }


}
