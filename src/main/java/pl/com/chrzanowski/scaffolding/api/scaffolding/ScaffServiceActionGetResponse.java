package pl.com.chrzanowski.scaffolding.api.scaffolding;


import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsData;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScaffServiceActionGetResponse {

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

    public ScaffServiceActionGetResponse(ScaffServiceActionsData data) {
        this.id = data.getId();
        this.vehicleId = data.getVehicleId();
        this.carMileage = data.getCarMileage();
        this.serviceDate = data.getServiceDate();
        this.invoiceNumber = data.getInvoiceNumber();
        this.workshopId = data.getWorkshopId();
        this.workshopName = data.getWorkshopName();
        this.serviceActionTypeId = data.getServiceActionTypeId();
        this.serviceActionTypeName = data.getServiceActionTypeName();
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
