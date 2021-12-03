package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;
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
    private ServiceActionTypesService serviceActionTypesService;

    public WorkshopsService(WorkshopsJdbcRepository workshopsJdbcRepository,
                            WorkshopServiceTypeService workshopServiceTypeService,
                            ServiceActionTypesService serviceActionTypesService) {
        this.workshopsJdbcRepository = workshopsJdbcRepository;
        this.workshopServiceTypeService = workshopServiceTypeService;
        this.serviceActionTypesService = serviceActionTypesService;

    }

    public List<WorkshopsData> find(WorkshopsFilter filter) {
        return getWorkshops(workshopsJdbcRepository.find(filter));
    }

    public List<WorkshopsData> findWithActionTypes(WorkshopsFilter filter) {
        List<WorkshopsData> workshops = find(filter);
        List<WorkshopsData> workshopsWithActionTypes = new ArrayList<>();
        if (workshops == null) {
            workshopsWithActionTypes = null;
        } else {
            for (WorkshopsData data : workshops) {
                workshopsWithActionTypes.add(new WorkshopsData(
                        data,
                        workshopServiceTypeService.getActionTypesForWorkshop(data),
                        findServiceWorkshopsById(data)
                ));
            }
        }
        return workshopsWithActionTypes;
    }

    public List<ServiceActionTypeData> findServiceWorkshopsById(WorkshopsData data) {
        List<ServiceActionTypeData> serviceActionTypeData = serviceActionTypesService.find(new ServiceActionTypesFilter());
        Long[] workshopServicesTypes = workshopServiceTypeService.getActionTypesForWorkshop(data);
        List<ServiceActionTypeData> result = new ArrayList<>();

        for (Long workshopServicesType : workshopServicesTypes) {
            for (ServiceActionTypeData type : serviceActionTypeData) {
                if (workshopServicesType.equals(type.getId())) {
                    result.add(new ServiceActionTypeData(type.getId(), type.getName()));
                }
            }
        }
        return result;
    }


    public Long add(WorkshopsData data) {
        validateData(data);
        Long workshopId = workshopsJdbcRepository.create(data);
        workshopServiceTypeService.validateAndCreateActionTypesForWorkshop(new WorkshopsData(workshopId, data));
        return workshopId;
    }

    public void update(WorkshopsData data) {
        validateData(data);
        if (data.getActionTypes() != null) {
            workshopServiceTypeService.deleteActionTypes(data);
            workshopServiceTypeService.validateAndCreateActionTypesForWorkshop(data);
        } else {
            data = new WorkshopsData(data, workshopServiceTypeService.getActionTypesForWorkshop(data));

        }
        workshopsJdbcRepository.update(data);
    }

    public void remove(WorkshopsData data) {
        validateData(data);
        if (data.getActionTypes() != null) {
            workshopServiceTypeService.deleteActionTypes(data);
            workshopServiceTypeService.validateAndCreateActionTypesForWorkshop(data);
        } else {
            data = new WorkshopsData(data, workshopServiceTypeService.getActionTypesForWorkshop(data));

        }
        workshopsJdbcRepository.remove(data);
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

    private void validateData(WorkshopsData data) {
        DataValidationUtil.validateTextField(data.getName(), "Nazwa");
        DataValidationUtil.validateTextField(data.getTaxNumber(), "NIP");
        DataValidationUtil.validateTextField(data.getStreet(), "Ulica");
        DataValidationUtil.validateTextField(data.getBuildingNo(), "Numer budynku");
        DataValidationUtil.validateTextField(data.getPostalCode(), "Kod pocztowy");
        DataValidationUtil.validateTextField(data.getCity(), "Miasto");
        DataValidationUtil.validateServiceTypes(data.getActionTypes());
    }



}
