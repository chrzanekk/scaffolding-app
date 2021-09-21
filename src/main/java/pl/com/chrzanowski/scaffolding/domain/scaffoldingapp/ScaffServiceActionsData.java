package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScaffServiceActionsData {

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

    public ScaffServiceActionsData(Long id, Long vehicleId, Integer carMileage, LocalDate serviceDate, String invoiceNumber, String serviceWorkshop, Long serviceActionTypeId, LocalDateTime createDate, LocalDateTime modifyDate, LocalDateTime removeDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.serviceWorkshop = serviceWorkshop;
        this.serviceActionTypeId = serviceActionTypeId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
    }

    public ScaffServiceActionsData(Long id, Long vehicleId, Integer carMileage, LocalDate serviceDate, String invoiceNumber, String serviceWorkshop, String serviceActionName, String serviceActionDescription) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.serviceWorkshop = serviceWorkshop;
        this.serviceActionName = serviceActionName;
        this.serviceActionDescription = serviceActionDescription;
        this.modifyDate = LocalDateTime.now();
    }

    public ScaffServiceActionsData(Long vehicleId, Integer carMileage, LocalDate serviceDate, String invoiceNumber, String serviceWorkshop, String serviceActionName, String serviceActionDescription) {
        this.vehicleId = vehicleId;
        this.carMileage = carMileage;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.serviceWorkshop = serviceWorkshop;
        this.serviceActionName = serviceActionName;
        this.serviceActionDescription = serviceActionDescription;
        this.createDate = LocalDateTime.now();
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
