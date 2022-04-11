package pl.com.chrzanowski.scaffolding.api.serviceactions;


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
    private String modifyDate;
    private String removeDate;


    public ServiceActionGetResponse(Long id,
                                    Long vehicleId,
                                    Integer carMileage,
                                    String serviceDate,
                                    String invoiceNumber,
                                    String invoiceGrossValue,
                                    String taxValue,
                                    String taxRate,
                                    String invoiceNetValue,
                                    Long workshopId,
                                    String workshopName,
                                    Long serviceActionTypeId,
                                    String serviceActionTypeName,
                                    WorkshopsData workshopsData,
                                    String serviceActionDescription,
                                    String modifyDate,
                                    String removeDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceGrossValue = invoiceGrossValue;
        this.taxValue = taxValue;
        this.taxRate = taxRate;
        this.invoiceNetValue = invoiceNetValue;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypeName = serviceActionTypeName;
        this.workshopsData = workshopsData;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
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

    public String getModifyDate() {
        return modifyDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }
}
