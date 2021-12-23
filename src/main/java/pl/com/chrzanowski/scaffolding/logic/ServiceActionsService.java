package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class ServiceActionsService implements IServiceActions {

    private ServiceActionsJdbcRepository serviceActionsJdbcRepository;
    private UserService usersService;
    private UserAuthoritiesService userAuthoritiesService;
    private WorkshopServiceTypeService workshopServiceTypeService;
    private DictionariesService dictionariesService;

    public ServiceActionsService(ServiceActionsJdbcRepository serviceActionsJdbcRepository,
                                 UserService usersService,
                                 UserAuthoritiesService userAuthoritiesService,
                                 WorkshopServiceTypeService workshopServiceTypeService,
                                 DictionariesService dictionariesService) {
        this.serviceActionsJdbcRepository = serviceActionsJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
        this.workshopServiceTypeService = workshopServiceTypeService;
        this.dictionariesService = dictionariesService;
    }

    public List<ServiceActionsData> find(ServiceActionsFilter filter) {
        return getActions(serviceActionsJdbcRepository.find(filter));
    }

    public ServiceActionsData findById(ServiceActionsFilter filter) {
        return getActions(serviceActionsJdbcRepository.find(filter)).get(0);
    }

    public ServiceActionsInvoiceSummaryData getActionInvoicesSummary(ServiceActionsFilter filter) {
        return getSummaryOfInvoices(serviceActionsJdbcRepository.findSummaryInvoiceValues(filter));
    }


    public Long add(ServiceActionsData data) {
        validateData(data);
        validateOilServiceStatus(data, getLastActionByType(serviceActionsJdbcRepository.findLastDateOfServiceType(
                new ServiceActionsFilter(data.getVehicleId(), data.getServiceActionTypeId(), false))));
        if (checkWorkshopServiceType(data)) {
            return serviceActionsJdbcRepository.create(new ServiceActionsData(
                    data,
                    TaxCalculationUtil.calculateTaxValue(data.getInvoiceNetValue(), data.getTaxRate()),
                    TaxCalculationUtil.calculateGrossValue(data.getInvoiceNetValue(), data.getTaxRate())));
        } else {
            throw new IllegalArgumentException("Ten warsztat nie wykonuje tej usługi.");
        }
    }
//fix modify and remove date
    public void update(ServiceActionsData data) {
        validateData(data);
        if (checkWorkshopServiceType(data)) {
            serviceActionsJdbcRepository.update(new ServiceActionsData(
                    TaxCalculationUtil.calculateTaxValue(data.getInvoiceNetValue(), data.getTaxRate()),
                    TaxCalculationUtil.calculateGrossValue(data.getInvoiceNetValue(), data.getTaxRate()),
                    data));
        } else {
            throw new IllegalArgumentException("Ten warsztat nie wykonuje tej usługi.");
        }
    }


    private List<ServiceActionsData> getActions(List<Map<String, Object>> data) {

        List<ServiceActionsData> list = new ArrayList<>();

        for (Map<String, Object> row : data) {
            list.add(new ServiceActionsData(
                    getLong(row, "id"),
                    getLong(row, "vehicle_id"),
                    getInteger(row, "car_mileage"),
                    getDate(row, "service_date"),
                    getString(row, "invoice_no"),
                    getBigDecimal(row, "invoice_gross_value"),
                    getBigDecimal(row, "invoice_net_value"),
                    getBigDecimal(row, "tax_value"),
                    getBigDecimal(row,"tax_rate"),
                    getLong(row, "workshop_id"),
                    getLong(row, "service_action_type_id"),
                    getString(row, "action_type"),
                    getString(row, "workshop"),
                    new WorkshopsData(
                            getLong(row, "workshopId"),
                            getString(row, "workshop"),
                            getString(row, "tax_number"),
                            getString(row, "street"),
                            getString(row, "building_number"),
                            getString(row, "apartment_number"),
                            getString(row, "postal_code"),
                            getString(row, "city"),
                            getDateTime(row, "modify_date"),
                            getDateTime(row, "remove_date")),
                    getString(row, "description"),
                    getDateTime(row, "modify_date"),
                    getDateTime(row, "remove_date")
            ));
        }
        return list;
    }

    private ServiceActionsInvoiceSummaryData getSummaryOfInvoices(List<Map<String, Object>> data) {
        List<ServiceActionsInvoiceSummaryData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            if (!row.containsValue(null)) {
                list.add(new ServiceActionsInvoiceSummaryData(
                        getBigDecimal(row, "summaryNetValue"),
                        getBigDecimal(row, "summaryTaxValue"),
                        getBigDecimal(row, "summaryGrossValue")
                ));
            } else {
                list.add(new ServiceActionsInvoiceSummaryData(
                        new BigDecimal("0.00"),
                        new BigDecimal("0.00"),
                        new BigDecimal("0.00")
                ));
            }
        }
        return list.get(0);
    }

    private ServiceActionsData getLastActionByType(Map<String, Object> data) {
        return new ServiceActionsData(
                getLong(data, "id"),
                getLong(data, "vehicle_id"),
                getDate(data, "service_date"),
                getLong(data, "service_action_type_id"),
                getString(data, "action_type")
        );
    }

    private Boolean checkWorkshopServiceType(ServiceActionsData data) {
        List<WorkshopServiceTypeData> availableServices = workshopServiceTypeService.find(new WorkshopServiceTypeFilter(data.getWorkshopId()));
        for (WorkshopServiceTypeData service : availableServices) {
            if (data.getServiceActionTypeId().equals(service.getServiceActionTypeId())) {
                return true;
            }
        }
        return false;
    }

    private void validateData(ServiceActionsData data) {
        DataValidationUtil.validateTextField(data.getInvoiceNumber(), "Numer faktury");
        DataValidationUtil.validateDate(data.getServiceDate(), "Data wykonania");
        DataValidationUtil.validateValue(data.getWorkshopId(), "Miejsce wykonania usługi");
        DataValidationUtil.validateValue(data.getServiceActionTypeId(), "Skrócony typ usługi");
        DataValidationUtil.validateCarMileage(data.getCarMileage(), "Przebieg");
        DataValidationUtil.validateTextField(data.getServiceActionDescription(), "Opis szczełowy wykonanych prac");
    }

    public static void validateOilServiceStatus(ServiceActionsData data, ServiceActionsData lastAction) {
        if (lastAction != null) {
            if (data.getServiceDate().compareTo(lastAction.getServiceDate()) <= 7) {
                throw new IllegalArgumentException("Usługa \"" + lastAction.getServiceActionTypeName() + "\" była " +
                        "wykonywana w przeciągu 7 dni.(" + lastAction.getServiceDate() + ")");
            }
        }
    }



}
