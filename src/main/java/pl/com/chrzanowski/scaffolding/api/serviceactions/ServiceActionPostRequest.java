package pl.com.chrzanowski.scaffolding.api.serviceactions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceActionPostRequest {

    private Long id;
    private Long vehicleId;
    private Integer carMileage;
    private LocalDate serviceDate;
    private String invoiceNumber;
    private String invoiceGrossValue;
    private String taxValue;
    private String taxRate;
    private String invoiceNetValue;
    private Long workshopId;
    private Long serviceActionTypeId;
    private String serviceActionName;
    private String serviceActionDescription;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ServiceActionPostRequest() {
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Integer getCarMileage() {
        return carMileage;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getInvoiceGrossValue() {
        return invoiceGrossValue;
    }

    public String getTaxValue() {
        return taxValue;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public String getInvoiceNetValue() {
        return invoiceNetValue;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public Long getServiceActionTypeId() {
        return serviceActionTypeId;
    }

    public String getServiceActionName() {
        return serviceActionName;
    }

    public String getServiceActionDescription() {
        return serviceActionDescription;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }
}
