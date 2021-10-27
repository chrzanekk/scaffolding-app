package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getLong;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;


@Service
public class WorkshopsService {

    private WorkshopsJdbcRepository workshopsJdbcRepository;
    private WorkshopServiceTypeService workshopServiceTypeService;

    public WorkshopsService(WorkshopsJdbcRepository workshopsJdbcRepository,
                            WorkshopServiceTypeService workshopServiceTypeService) {
        this.workshopsJdbcRepository = workshopsJdbcRepository;
        this.workshopServiceTypeService = workshopServiceTypeService;
    }

    public List<WorkshopsData> find(WorkshopsFilter filter) {
        return getWorkshops(workshopsJdbcRepository.find(filter));
    }

    public List<WorkshopsData> findWithActionTypes(WorkshopsFilter filter) {
        List<WorkshopsData> workshops = find(filter);
        List<WorkshopsData> workshopsWitActionTypes = new ArrayList<>();
        if (workshops == null) {
            workshopsWitActionTypes = null;
        } else {
            for (WorkshopsData data : workshops) {
                workshopsWitActionTypes.add(new WorkshopsData(
                        data,
                        workshopServiceTypeService.getActionTypesForWorkshop(data)
                ));
            }
        }
        return workshopsWitActionTypes;
    }

    public Long add(WorkshopsData data) {
        return workshopsJdbcRepository.create(data);
    }

    public void update(WorkshopsData data) {
        if (data.getActionTypes() != null) {
            workshopServiceTypeService.deleteActionTypes(data);
            workshopServiceTypeService.validateAndCreateActionTypesForWorkshop(data);
        } else {
            data = new WorkshopsData(data, workshopServiceTypeService.getActionTypesForWorkshop(data));

        }
        workshopsJdbcRepository.update(data);
    }

    private List<WorkshopsData> getWorkshops(List<Map<String, Object>> data) {
        List<WorkshopsData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new WorkshopsData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getString(row, "tax_number"),
                    getString(row, "street"),
                    getString(row, "building_number"),
                    getString(row, "apartment_number"),
                    getString(row, "postal_code"),
                    getString(row, "city")
            ));
        }
        return list;
    }
}