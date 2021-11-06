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

    public ServiceActionsService(ServiceActionsJdbcRepository serviceActionsJdbcRepository,
                                 UserService usersService,
                                 UserAuthoritiesService userAuthoritiesService,
                                 WorkshopServiceTypeService workshopServiceTypeService) {
        this.serviceActionsJdbcRepository = serviceActionsJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
        this.workshopServiceTypeService = workshopServiceTypeService;
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
        if (checkWorkshopServiceType(data)) {
            return serviceActionsJdbcRepository.create(new ServiceActionsData(
                    data,
                    calculateTaxValue(data.getInvoiceGrossValue()),
                    calculateNetValue(data.getInvoiceGrossValue())));
        } else {
            throw new IllegalArgumentException("Ten warsztat nie wykonuje tej usługi.");
        }
    }

    public void update(ServiceActionsData data) {
        if (checkWorkshopServiceType(data)) {
            serviceActionsJdbcRepository.update(new ServiceActionsData(
                    calculateTaxValue(data.getInvoiceGrossValue()),
                    calculateNetValue(data.getInvoiceGrossValue()),
                    data));
        } else {
            throw new IllegalArgumentException("Ten warsztat nie wykonuje tej usługi.");
        }
    }

    public void delete(ServiceActionsData data) {
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


    private BigDecimal calculateNetValue(BigDecimal grossValue) {
        BigDecimal taxRate = new BigDecimal("1.23").setScale(2, RoundingMode.HALF_EVEN);
        return grossValue.setScale(2, RoundingMode.HALF_EVEN).divide(taxRate, 2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateTaxValue(BigDecimal grossValue) {
        BigDecimal netValue = calculateNetValue(grossValue);
        return grossValue.subtract(netValue).setScale(2, RoundingMode.HALF_EVEN);
    }
}
