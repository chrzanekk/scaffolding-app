package pl.com.chrzanowski.scaffolding.logic.serviceactions;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pl.com.chrzanowski.scaffolding.Application;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsDemandResultData;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsInvoiceSummaryData;
import pl.com.chrzanowski.scaffolding.domain.vehicles.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.vehicles.VehicleFilter;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopServiceTypeData;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopServiceTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.workshops.WorkshopsData;
import pl.com.chrzanowski.scaffolding.logic.IServiceActions;
import pl.com.chrzanowski.scaffolding.logic.IVehicles;
import pl.com.chrzanowski.scaffolding.logic.workshops.WorkshopServiceTypeService;
import pl.com.chrzanowski.scaffolding.logic.dictionaries.DictionariesService;
import pl.com.chrzanowski.scaffolding.logic.user.UserAuthoritiesService;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;
import pl.com.chrzanowski.scaffolding.logic.utils.TaxCalculationUtil;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class ServiceActionsService implements IServiceActions {

    private static final Logger log = LoggerFactory.getLogger(ServiceActionsService.class);
    private ServiceActionsJdbcRepository serviceActionsJdbcRepository;
    private UserService usersService;
    private UserAuthoritiesService userAuthoritiesService;
    private WorkshopServiceTypeService workshopServiceTypeService;
    private DictionariesService dictionariesService;
    private ApplicationConfig applicationConfig;
    private SpringTemplateEngine templateEngine;
    private IVehicles vehiclesService;

    public ServiceActionsService(ServiceActionsJdbcRepository serviceActionsJdbcRepository,
                                 UserService usersService,
                                 UserAuthoritiesService userAuthoritiesService,
                                 WorkshopServiceTypeService workshopServiceTypeService,
                                 DictionariesService dictionariesService,
                                 ApplicationConfig applicationConfig,
                                 SpringTemplateEngine templateEngine,
                                 IVehicles vehiclesService) {
        this.serviceActionsJdbcRepository = serviceActionsJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
        this.workshopServiceTypeService = workshopServiceTypeService;
        this.dictionariesService = dictionariesService;
        this.applicationConfig = applicationConfig;
        this.templateEngine = templateEngine;
        this.vehiclesService = vehiclesService;
    }

    public List<ServiceActionsData> find(ServiceActionsFilter filter) {
        return getActions(serviceActionsJdbcRepository.find(filter));
    }

    public ServiceActionsData findById(ServiceActionsFilter filter) {
        return getActions(serviceActionsJdbcRepository.find(filter)).get(0);
    }

    public ServiceActionsInvoiceSummaryData findActionInvoicesSummary(ServiceActionsFilter filter) {
        return getSummaryOfInvoices(serviceActionsJdbcRepository.findSummaryInvoiceValues(filter));
    }

    public ServiceActionsDemandResultData findActionsDemandWithSummary(ServiceActionsFilter filter) {
        ServiceActionsInvoiceSummaryData summaryData =
                getSummaryOfInvoices(serviceActionsJdbcRepository.findSummaryInvoiceValues(filter));
        List<ServiceActionsData> serviceActionsData = getActionsWithOrdinalNumber(serviceActionsJdbcRepository.find(filter));
        VehicleData vehicleData = vehiclesService.findById(new VehicleFilter(filter.getVehicleId()));


        return new ServiceActionsDemandResultData(
                summaryData.getSummaryNetValue(),
                summaryData.getSummaryTaxValue(),
                summaryData.getSummaryGrossValue(),
                serviceActionsData,
                vehicleData,
                DateUtil.formatDate(LocalDateTime.now())
                );
    }

    public ByteArrayInputStream getActionsDemandWithSummaryPdf(ServiceActionsFilter filter) {
        ServiceActionsDemandResultData data = findActionsDemandWithSummary(filter);
        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(
                    exportToPdfBox(fillPdfVariable(data), applicationConfig.getTemplateNameServiceActionsDemandPdf(),
                            applicationConfig.getPathToServiceActionsPdf() + getServiceActionsDemandPdfName())
            ));
        } catch (IOException e) {
            log.error("Exception while tranforming to ByteArrayInputStream : {}", e);
        }
        return null;
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


    public static void validateOilServiceStatus(ServiceActionsData data, ServiceActionsData lastAction) {
        if (lastAction != null) {
            if (data.getServiceDate().compareTo(lastAction.getServiceDate()) <= 7) {
                throw new IllegalArgumentException("Usługa \"" + lastAction.getServiceActionTypeName() + "\" była " +
                        "wykonywana w przeciągu 7 dni.(" + lastAction.getServiceDate() + ")");
            }
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

    private List<ServiceActionsData> getActionsWithOrdinalNumber(List<Map<String,Object>> data) {
        List<ServiceActionsData> list = new ArrayList<>();
        Long ordinalNumber = 1L;
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
                    getDateTime(row, "remove_date"),
                    ordinalNumber
            ));
            ordinalNumber++;
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

    private File exportToPdfBox(Map<String, Object> variables, String templatePath, String outPath) {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        try (OutputStream os = new FileOutputStream(outPath)) {
            log.error("Fonts dir: " + applicationConfig.getPathToFonts());
            if (applicationConfig.getPathToFonts() == null || "".equals(applicationConfig.getPathToFonts())) {
                builder.useFont(new File(Application.class.getClassLoader().getResource("static/fonts/dejavu" +
                        "/DejaVuSans.ttf").getFile()),"dejavu");
            } else {
                builder.useFont(new File(applicationConfig.getPathToFonts() + "DejaVuSans.ttf"),"dejavu");
            }

            String html = getHtmlString(variables, templatePath);
            log.trace(html);
            builder.withHtmlContent(html, "file:");
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            log.error("Exception while generating pdf : {}", e);
        }
        return new File(outPath);
    }

    private String getHtmlString(Map<String,Object> variables, String templatePath) {
        final Context ctx = new Context();
        ctx.setVariables(variables);
        try{
            return templateEngine.process(templatePath, ctx);
        } catch (Exception e) {
            log.error("Exception while getting html string from template engine : {}", e);
            return null;
        }
    }

    private Map<String, Object> fillPdfVariable(ServiceActionsDemandResultData data) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("list", data.getServiceActions());
        vars.put("summaryNetValue", data.getSummaryNetValue());
        vars.put("summaryTaxValue", data.getSummaryTaxValue());
        vars.put("summaryGrossValue", data.getSummaryGrossValue());
        vars.put("vehicle", data.getVehicleData());
        vars.put("createDate", data.getCreateDate());
        return vars;
    }

    private String getServiceActionsDemandPdfName() {
        return "export_" + getUnique() + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ".pdf";
    }

    private String getUnique() {
        long time = System.currentTimeMillis();
        return String.format("%s%08x%05x", "", time / 1000, time);
    }

}
