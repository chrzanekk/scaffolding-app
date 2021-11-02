package pl.com.chrzanowski.scaffolding.domain;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceActionsData {

    private Long id;
    private Long vehicleId;
    private Integer carMileage;
    private LocalDate serviceDate;
    private String invoiceNumber;
    private BigDecimal invoiceGrossValue;
    private BigDecimal taxValue;
    private BigDecimal invoiceNetValue;
    private Long workshopId;
    private String workshopName;
    private WorkshopsData workshopsData;
    private Long serviceActionTypeId;
    private String serviceActionTypeName;
    private String serviceActionDescription;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ServiceActionsData(Long id,
                              Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceGrossValue,
                              BigDecimal invoiceNetValue,
                              BigDecimal taxValue,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String actionTypeName,
                              String workshopName,
                              WorkshopsData workshopsData,
                              String serviceActionDescription) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceGrossValue = invoiceGrossValue;
        this.invoiceNetValue = invoiceNetValue;
        this.taxValue = taxValue;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.workshopsData = workshopsData;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypeName = actionTypeName;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = LocalDateTime.now();
    }

    public ServiceActionsData(Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceGrossValue,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String serviceActionDescription) {
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceGrossValue = invoiceGrossValue;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionDescription = serviceActionDescription;
        this.createDate = LocalDateTime.now();
    }

    public ServiceActionsData(Long id,
                              Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceGrossValue,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String serviceActionDescription) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceGrossValue =invoiceGrossValue;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = LocalDateTime.now();
    }

    public ServiceActionsData(ServiceActionsData data, BigDecimal taxValue, BigDecimal invoiceNetValue) {
        this.vehicleId = data.getVehicleId();
        this.carMileage = data.getCarMileage();
        this.serviceDate = data.getServiceDate();
        this.invoiceNumber = data.getInvoiceNumber();
        this.invoiceGrossValue = data.getInvoiceGrossValue();
        this.workshopId = data.getWorkshopId();
        this.serviceActionTypeId = data.getServiceActionTypeId();
        this.serviceActionDescription = data.getServiceActionDescription();
        this.createDate = LocalDateTime.now();
        this.taxValue = taxValue;
        this.invoiceNetValue = invoiceNetValue;
    }

    public ServiceActionsData(BigDecimal taxValue, BigDecimal invoiceNetValue, ServiceActionsData data) {
        this.id = data.getId();
        this.vehicleId = data.getVehicleId();
        this.carMileage = data.getCarMileage();
        this.serviceDate = data.getServiceDate();
        this.invoiceNumber = data.getInvoiceNumber();
        this.invoiceGrossValue = data.getInvoiceGrossValue();
        this.workshopId = data.getWorkshopId();
        this.serviceActionTypeId = data.getServiceActionTypeId();
        this.serviceActionDescription = data.getServiceActionDescription();
        this.taxValue = taxValue;
        this.invoiceNetValue = invoiceNetValue;
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

    public BigDecimal getInvoiceGrossValue() {
        return invoiceGrossValue;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public BigDecimal getInvoiceNetValue() {
        return invoiceNetValue;
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

    public WorkshopsData getWorkshopsData() {
        return workshopsData;
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
