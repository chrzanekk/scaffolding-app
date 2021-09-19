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
    private String serviceWorkshop;
    private Long serviceActionTypeId;
    private String serviceActionName;
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
        this.serviceWorkshop = data.getServiceWorkshop();
        this.serviceActionName = data.getServiceActionName();
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

    public String getServiceWorkshop() {
        return serviceWorkshop;
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
