package pl.com.chrzanowski.scaffolding.api;


import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceActionGetResponse {

    private Long id;
    private Long vehicleId;
    private Integer carMileage;
    private String serviceDate;
    private String invoiceNumber;
    private String invoiceGrossValue;
    private String taxValue;
    private String taxRate;
    private String invoiceNetValue;
    private Long workshopId;
    private String workshopName;
    private Long serviceActionTypeId;
    private String serviceActionTypeName;
    private WorkshopsData workshopsData;
    private String serviceActionDescription;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ServiceActionGetResponse(ServiceActionsData data) {
        this.id = data.getId();
        this.vehicleId = data.getVehicleId();
        this.carMileage = data.getCarMileage();
        this.serviceDate = data.getServiceDate().toString();
        this.invoiceNumber = data.getInvoiceNumber();
        this.invoiceGrossValue = data.getInvoiceGrossValue().toString();
        this.invoiceNetValue = data.getInvoiceNetValue().toString();
        this.taxValue = data.getTaxValue().toString();
        this.taxRate = data.getTaxRate().toString();
        this.workshopId = data.getWorkshopId();
        this.workshopName = data.getWorkshopName();
        this.serviceActionTypeId = data.getServiceActionTypeId();
        this.serviceActionTypeName = data.getServiceActionTypeName();
        this.workshopsData = data.getWorkshopsData();
        this.serviceActionDescription = data.getServiceActionDescription();
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

    public String getServiceDate() {
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

    public WorkshopsData getWorkshopsData() {
        return workshopsData;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public Long getServiceActionTypeId() {
        return serviceActionTypeId;
    }

    public String getServiceActionTypeName() {
        return serviceActionTypeName;
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
