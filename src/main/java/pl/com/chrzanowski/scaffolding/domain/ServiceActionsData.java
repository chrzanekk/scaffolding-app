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
    private BigDecimal taxRate;
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
    private Long ordinalNumber;


    //  find method in service
    public ServiceActionsData(Long id,
                              Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceGrossValue,
                              BigDecimal invoiceNetValue,
                              BigDecimal taxValue,
                              BigDecimal taxRate,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String actionTypeName,
                              String workshopName,
                              WorkshopsData workshopsData,
                              String serviceActionDescription,
                              LocalDateTime modifyDate,
                              LocalDateTime removeDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceGrossValue = invoiceGrossValue;
        this.invoiceNetValue = invoiceNetValue;
        this.taxValue = taxValue;
        this.taxRate = taxRate;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.workshopsData = workshopsData;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypeName = actionTypeName;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }

    public ServiceActionsData(Long id,
                              Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceGrossValue,
                              BigDecimal invoiceNetValue,
                              BigDecimal taxValue,
                              BigDecimal taxRate,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String actionTypeName,
                              String workshopName,
                              WorkshopsData workshopsData,
                              String serviceActionDescription,
                              LocalDateTime modifyDate,
                              LocalDateTime removeDate,
                              Long ordinalNumber) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceGrossValue = invoiceGrossValue;
        this.invoiceNetValue = invoiceNetValue;
        this.taxValue = taxValue;
        this.taxRate = taxRate;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.workshopsData = workshopsData;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypeName = actionTypeName;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
        this.ordinalNumber = ordinalNumber;
    }

    //post method in api
    public ServiceActionsData(Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceNetValue,
                              BigDecimal taxRate,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String serviceActionDescription) {
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceNetValue = invoiceNetValue;
        this.taxRate = taxRate;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionDescription = serviceActionDescription;
        this.createDate = LocalDateTime.now();
    }

    //put method in api (to update, remove and restore)
    public ServiceActionsData(Long id,
                              Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              BigDecimal invoiceNetValue,
                              BigDecimal taxRate,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String serviceActionDescription,
                              LocalDateTime modifyDate,
                              LocalDateTime removeDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceNetValue = invoiceNetValue;
        this.taxRate = taxRate;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }

    //add method in service
    public ServiceActionsData(ServiceActionsData data, BigDecimal taxValue, BigDecimal invoiceGrossValue) {
        this.vehicleId = data.getVehicleId();
        this.carMileage = data.getCarMileage();
        this.serviceDate = data.getServiceDate();
        this.invoiceNumber = data.getInvoiceNumber();
        this.invoiceGrossValue = invoiceGrossValue;
        this.invoiceNetValue = data.getInvoiceNetValue();
        this.taxRate = data.getTaxRate();
        this.workshopId = data.getWorkshopId();
        this.serviceActionTypeId = data.getServiceActionTypeId();
        this.serviceActionDescription = data.getServiceActionDescription();
        this.createDate = LocalDateTime.now();
        this.taxValue = taxValue;
    }

    //    update method in service
    public ServiceActionsData(BigDecimal taxValue, BigDecimal invoiceGrossValue, ServiceActionsData data) {
        this.id = data.getId();
        this.vehicleId = data.getVehicleId();
        this.carMileage = data.getCarMileage();
        this.serviceDate = data.getServiceDate();
        this.invoiceNumber = data.getInvoiceNumber();
        this.invoiceGrossValue = invoiceGrossValue;
        this.workshopId = data.getWorkshopId();
        this.serviceActionTypeId = data.getServiceActionTypeId();
        this.serviceActionDescription = data.getServiceActionDescription();
        this.modifyDate = LocalDateTime.now();
        this.removeDate = data.getRemoveDate();
        this.taxValue = taxValue;
        this.invoiceNetValue = data.getInvoiceNetValue();
        this.taxRate = data.getTaxRate();
    }


    //get last action type + private constructor for static method
    public ServiceActionsData(Long id, Long vehicleId, LocalDate serviceDate, Long serviceActionTypeId, String serviceActionTypeName) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.serviceDate = serviceDate;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypeName = serviceActionTypeName;
    }

    static ServiceActionsData createForLastActionType(Long id, Long vehicleId, LocalDate serviceDate,
                                                      Long serviceActionTypeId, String serviceActionTypeName) {
        return new ServiceActionsData(id, vehicleId, serviceDate, serviceActionTypeId, serviceActionTypeName);
    }

    static ServiceActionsData createForPdfDemandData(ServiceActionsData data, Long ordinalNumber) {
        return new ServiceActionsData(data.getId(),
                data.getVehicleId(),
                data.getCarMileage(),
                data.getServiceDate(),
                data.getInvoiceNumber(),
                data.getInvoiceGrossValue(),
                data.getInvoiceNetValue(),
                data.getTaxValue(),
                data.getTaxRate(),
                data.getWorkshopId(),
                data.getServiceActionTypeId(),
                data.getServiceActionTypeName(),
                data.getWorkshopName(),
                data.getWorkshopsData(),
                data.getServiceActionDescription(),
                data.getModifyDate(),
                data.getRemoveDate(),
                ordinalNumber
                );
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

    public BigDecimal getTaxRate() {
        return taxRate;
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

    public Long getOrdinalNumber() {
        return ordinalNumber;
    }
}
