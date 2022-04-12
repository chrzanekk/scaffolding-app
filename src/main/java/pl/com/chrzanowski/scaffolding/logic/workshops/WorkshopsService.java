package pl.com.chrzanowski.scaffolding.logic.workshops;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypesFilter;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopsFilter;
import pl.com.chrzanowski.scaffolding.logic.serviceactiontypes.ServiceActionTypeService;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;


@Service
public class WorkshopsService {

    private WorkshopsJdbcRepository workshopsJdbcRepository;
    private WorkshopServiceTypeService workshopServiceTypeService;
    private ServiceActionTypeService serviceActionTypeService;

    public WorkshopsService(WorkshopsJdbcRepository workshopsJdbcRepository,
                            WorkshopServiceTypeService workshopServiceTypeService,
                            ServiceActionTypeService serviceActionTypeService) {
        this.workshopsJdbcRepository = workshopsJdbcRepository;
        this.workshopServiceTypeService = workshopServiceTypeService;
        this.serviceActionTypeService = serviceActionTypeService;

    }

    public List<WorkshopsData> find(WorkshopsFilter filter) {
        return getWorkshops(workshopsJdbcRepository.find(filter));
    }

    public List<WorkshopsData> findWithActionTypes(List<WorkshopsData> workshops) {
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
        List<ServiceActionTypeData> serviceActionTypeData = serviceActionTypeService.find(new ServiceActionTypesFilter());
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
        WorkshopsData workshopsData = formatFields(data);
        validateData(workshopsData);
        Long workshopId = workshopsJdbcRepository.create(workshopsData);
        workshopServiceTypeService.validateAndCreateActionTypesForWorkshop(new WorkshopsData(workshopId, workshopsData));
        return workshopId;
    }

    public void update(WorkshopsData data) {
        WorkshopsData workshopsData = new WorkshopsData(formatFields(data), data.getId());
        validateData(workshopsData);
        if (data.getActionTypes() != null) {
            workshopServiceTypeService.deleteActionTypes(workshopsData);
            workshopServiceTypeService.validateAndCreateActionTypesForWorkshop(workshopsData);
        } else {
            workshopsData = new WorkshopsData(workshopsData, workshopServiceTypeService.getActionTypesForWorkshop(workshopsData));

        }
        workshopsJdbcRepository.update(workshopsData);
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
                    getString(row, "city"),
                    getDateTime(row, "modify_date"),
                    getDateTime(row, "remove_date")
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

    private WorkshopsData formatFields(WorkshopsData data) {
        String formattedTaxNumber = formatTaxNumber(data.getTaxNumber());
        String formattedPostalCode = formatPostalCode(data.getPostalCode());
        return new WorkshopsData(data, formattedTaxNumber, formattedPostalCode);
    }

    private String cleanInput(String input) {
        if (input != null) {
            return input.replaceAll("[^a-zA-Z0-9]", "");
        }
        return "";
    }

    private String formatTaxNumber(String taxNumber) {
        taxNumber = cleanInput(taxNumber);
        return taxNumber.substring(0, 3) + "-" + taxNumber.substring(3, 6) + "-" + taxNumber.substring(6, 8) + "-" + taxNumber.substring(8, 10);
    }

    private String formatPostalCode(String postalCode) {
        postalCode = cleanInput(postalCode);
        return postalCode.substring(0, 2) + "-" + postalCode.substring(2, 5);
    }
}




