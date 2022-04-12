package pl.com.chrzanowski.scaffolding.api;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.serviceactions.*;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.serviceactions.ServiceActionsInvoiceSummaryData;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;
import pl.com.chrzanowski.scaffolding.logic.IServiceActions;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminServiceActions {
    private IServiceActions serviceActions;

    public ScaffoldingEndpointAdminServiceActions(IServiceActions serviceActions) {
        this.serviceActions = serviceActions;

    }

    @GetMapping(path = "/vehicle-service-actions/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionsRequestGetResponse vehicleServiceActions(
            @PathVariable Long id,
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionsData> actions = serviceActions.find(new ServiceActionsFilter(
                id,
                actionTypeName,
                workshop,
                dateFrom,
                dateTo,
                page,
                pageSize,
                false));
        return new ServiceActionsRequestGetResponse(actionsToResponse(actions));
    }

    @GetMapping(path = "/removed-vehicle-service-actions/", produces = "application/json; charset=UTF-8")
    public ServiceActionsRequestGetResponse removedVehicleServiceActions(
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionsData> actions = serviceActions.find(new ServiceActionsFilter(
                null,
                actionTypeName,
                workshop,
                dateFrom,
                dateTo,
                page,
                pageSize,
                true));
        return new ServiceActionsRequestGetResponse(actionsToResponse(actions));
    }

    @GetMapping(path = "/vehicle-service-action-value-summary/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionsSummaryValueGetResponse serviceActionsInvoiceSummary(
            @PathVariable Long id,
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        ServiceActionsInvoiceSummaryData summary =
                serviceActions.findActionInvoicesSummary(new ServiceActionsFilter(id,
                        actionTypeName,
                        workshop,
                        dateFrom,
                        dateTo,
                        false));
        return summaryToResponse(summary);
    }

    @GetMapping(path = "/vehicle-service-action/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionRequestGetResponse vehicleServiceActionById(
            @PathVariable Long id) {
        ServiceActionsData serviceAction = serviceActions.findById(new ServiceActionsFilter(id, false));
        return new ServiceActionRequestGetResponse(actionToResponse(serviceAction));
    }

    @PostMapping(path = "/vehicle-service-action", consumes = "application/json; charset=UTF-8")
    public void addVehicleServiceAction(@RequestBody ServiceActionPostRequest request) {
        BigDecimal netValue = DataValidationUtil.validateAndCreateValue(request.getInvoiceNetValue());
        BigDecimal taxRate = DataValidationUtil.validateAndCreateValue(request.getTaxRate());
        serviceActions.add(new ServiceActionsData(
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                netValue,
                taxRate,
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription()));
    }

    //to put and restore
    @PutMapping(path = "/vehicle-service-action/{id}")
    public void updateServiceAction(@PathVariable Long id, @RequestBody ServiceActionPutRequest request) {
        BigDecimal netValue = DataValidationUtil.validateAndCreateValue(request.getInvoiceNetValue());
        BigDecimal taxRate = DataValidationUtil.validateAndCreateValue(request.getTaxRate());
        serviceActions.update(new ServiceActionsData(
                id,
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                netValue,
                taxRate,
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription(),
                request.getModifyDate(),
                null
        ));
    }

    @PutMapping(path = "/vehicle-service-action-to-remove/{id}")
    public void deleteServiceAction(@PathVariable Long id, @RequestBody ServiceActionPutRequest request) {
        BigDecimal netValue = DataValidationUtil.validateAndCreateValue(request.getInvoiceNetValue());
        BigDecimal taxRate = DataValidationUtil.validateAndCreateValue(request.getTaxRate());
        serviceActions.update(new ServiceActionsData(
                request.getId(),
                request.getVehicleId(),
                request.getCarMileage(),
                request.getServiceDate(),
                request.getInvoiceNumber(),
                netValue,
                taxRate,
                request.getWorkshopId(),
                request.getServiceActionTypeId(),
                request.getServiceActionDescription(),
                request.getModifyDate(),
                request.getRemoveDate()
        ));
    }

    @GetMapping(path = "vehicle-service-actions/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getServiceActionsPdf(
            @PathVariable Long id,
            @RequestParam(name = "serviceActionTypeName", required = false) String actionTypeName,
            @RequestParam(name = "workshopName", required = false) String workshop,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "1000000") Long pageSize) {
        ByteArrayInputStream bis = serviceActions.getActionsDemandWithSummaryPdf(new ServiceActionsFilter(
                id,
                actionTypeName,
                workshop,
                dateFrom,
                dateTo,
                page,
                1000000L,
                false));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=request_" + LocalDate.now() + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private List<ServiceActionGetResponse> actionsToResponse(List<ServiceActionsData> actions) {
        List<ServiceActionGetResponse> list = new ArrayList<>();
        for (ServiceActionsData data : actions) {
            list.add(new ServiceActionGetResponse(
                    data.getId(),
                    data.getVehicleId(),
                    data.getCarMileage(),
                    DateUtil.parseDate(data.getServiceDate()),
                    data.getInvoiceNumber(),
                    bigDecimalToString(data.getInvoiceGrossValue()),
                    bigDecimalToString(data.getTaxValue()),
                    bigDecimalToString(data.getTaxRate()),
                    bigDecimalToString(data.getInvoiceNetValue()),
                    data.getWorkshopId(),
                    data.getWorkshopName(),
                    data.getServiceActionTypeId(),
                    data.getServiceActionTypeName(),
                    data.getWorkshopsData(),
                    data.getServiceActionDescription(),
                    DateUtil.parseDateTime(data.getModifyDate()),
                    DateUtil.parseDateTime(data.getRemoveDate())));
        }
        return list;
    }

    private ServiceActionGetResponse actionToResponse(ServiceActionsData data) {
        return new ServiceActionGetResponse(
                data.getId(),
                data.getVehicleId(),
                data.getCarMileage(),
                DateUtil.parseDate(data.getServiceDate()),
                data.getInvoiceNumber(),
                bigDecimalToString(data.getInvoiceGrossValue()),
                bigDecimalToString(data.getTaxValue()),
                bigDecimalToString(data.getTaxRate()),
                bigDecimalToString(data.getInvoiceNetValue()),
                data.getWorkshopId(),
                data.getWorkshopName(),
                data.getServiceActionTypeId(),
                data.getServiceActionTypeName(),
                data.getWorkshopsData(),
                data.getServiceActionDescription(),
                DateUtil.parseDateTime(data.getModifyDate()),
                DateUtil.parseDateTime(data.getRemoveDate())
        );
    }

    private ServiceActionsSummaryValueGetResponse summaryToResponse(ServiceActionsInvoiceSummaryData data) {
        return new ServiceActionsSummaryValueGetResponse(
                data.getSummaryNetValue(),
                data.getSummaryTaxValue(),
                data.getSummaryGrossValue());
    }

    private String bigDecimalToString(BigDecimal value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
