package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public Boolean hasLoggedUserPermissionToActionsManagement() {

        UserData loggedUser = usersService.getLoggedUser();

        return userAuthoritiesService.hasUserAuthority(loggedUser, UserAuthority.ADMIN);

    }

    public Long add(ServiceActionsData data) {
        validateData(data);
        if (checkWorkshopServiceType(data)) {
            return serviceActionsJdbcRepository.create(new ServiceActionsData(
                    data,
                    calculateTaxValue(data.getInvoiceNetValue(), data.getTaxRate()),
                    calculateGrossValue(data.getInvoiceNetValue(), data.getTaxRate())));
        } else {
            throw new IllegalArgumentException("Ten warsztat nie wykonuje tej usługi.");
        }
    }

    public void update(ServiceActionsData data) {
        validateData(data);
        if (checkWorkshopServiceType(data)) {
            serviceActionsJdbcRepository.update(new ServiceActionsData(
                    calculateTaxValue(data.getInvoiceNetValue(), data.getTaxRate()),
                    calculateGrossValue(data.getInvoiceNetValue(), data.getTaxRate()),
                    data));
        } else {
            throw new IllegalArgumentException("Ten warsztat nie wykonuje tej usługi.");
        }
    }

    public void delete(ServiceActionsData data) {
    }

    public BigDecimal validateAndCreateValue(String value) {
        if (!value.isEmpty()) {
            if (isValuePositive(value)) {
                return new BigDecimal(value);
            } else {
                throw new IllegalArgumentException("Wartość nie może być ujemna.");
            }
        } else {
            throw new IllegalArgumentException("Wartość netto faktury lub stawka podatku VAT nie może być pusta.");
        }
    }


    public boolean isValuePositive(String value) {
        return !value.startsWith("-");
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
                            getString(row, "city")),
                    getString(row, "description")
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

    private Boolean checkWorkshopServiceType(ServiceActionsData data) {
        List<WorkshopServiceTypeData> availableServices = workshopServiceTypeService.find(new WorkshopServiceTypeFilter(data.getWorkshopId()));
        for (WorkshopServiceTypeData service : availableServices) {
            if (data.getServiceActionTypeId().equals(service.getServiceActionTypeId())) {
                return true;
            }
        }
        return false;
    }


    private BigDecimal calculateTaxValue(BigDecimal netValue, BigDecimal taxRate) {
        return calculateGrossValue(netValue, taxRate).subtract(netValue).setScale(2, RoundingMode.HALF_EVEN);

    }

    private BigDecimal calculateGrossValue(BigDecimal netValue, BigDecimal taxRate) {
        return netValue.setScale(2, RoundingMode.HALF_EVEN).multiply(taxRate.setScale(2, RoundingMode.HALF_EVEN));
    }

    private void validateData(ServiceActionsData data) {
        DataValidationUtil.validateTextField(data.getInvoiceNumber(), "Numer faktury");
        DataValidationUtil.validateDate(data.getServiceDate(), "Data wykonania");
        DataValidationUtil.validateValue(data.getWorkshopId(), "Miejsce wykonania usługi");
        DataValidationUtil.validateValue(data.getServiceActionTypeId(), "Skrócony typ usługi");
        DataValidationUtil.validateCarMileage(data.getCarMileage(), "Przebieg");
        DataValidationUtil.validateTextField(data.getServiceActionDescription(), "Opis szczełowy wykonanych prac");
    }





}
