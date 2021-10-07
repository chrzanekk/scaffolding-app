package pl.com.chrzanowski.scaffolding.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceActionsData {

    private Long id;
    private Long vehicleId;
    private Integer carMileage;
    private LocalDate serviceDate;
    private String invoiceNumber;
    private Long workshopId;
    private String workshopName;
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
                              Long workshopId,
                              Long serviceActionTypeId,
                              String actionTypeName,
                              String workshopName,
                              String serviceActionDescription) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypeName = actionTypeName;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = LocalDateTime.now();
    }

    public ServiceActionsData(Long vehicleId,
                              Integer carMileage,
                              LocalDate serviceDate,
                              String invoiceNumber,
                              Long workshopId,
                              Long serviceActionTypeId,
                              String serviceActionDescription) {
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
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
                              Long workshopId,
                              Long serviceActionTypeId,
                              String serviceActionDescription) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionDescription = serviceActionDescription;
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
