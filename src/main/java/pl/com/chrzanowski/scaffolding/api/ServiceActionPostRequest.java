package pl.com.chrzanowski.scaffolding.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceActionPostRequest {

    private Long id;
    private Long vehicleId;
    private Integer carMileage;
    private LocalDate serviceDate;
    private String invoiceNumber;
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
