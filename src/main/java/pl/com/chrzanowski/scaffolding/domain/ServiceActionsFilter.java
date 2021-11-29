package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDate;

public class ServiceActionsFilter {

    private Long id;
    private Long vehicleId;
    private LocalDate serviceDate;
    private String actionTypeName;
    private Long serviceActionId;
    private Long workshopId;
    private String workshopName;
    private Long page;
    private Long pageSize;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public ServiceActionsFilter(Long vehicleId,
                                Long page,
                                Long pageSize) {
        this.vehicleId = vehicleId;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ServiceActionsFilter(Long vehicleId,
                                String actionTypeName,
                                String workshopName,
                                LocalDate dateFrom,
                                LocalDate dateTo,
                                Long page,
                                Long pageSize) {
        this.vehicleId = vehicleId;
        this.actionTypeName = actionTypeName;
        this.workshopName = workshopName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ServiceActionsFilter(Long id) {
        this.id = id;
    }

    public ServiceActionsFilter(Long vehicleId, String actionTypeName, String workshopName, LocalDate dateFrom,
                                LocalDate dateTo) {
        this.vehicleId = vehicleId;
        this.actionTypeName = actionTypeName;
        this.workshopName = workshopName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public ServiceActionsFilter() {
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public String getActionTypeName() {
        return actionTypeName;
    }

    public Long getServiceActionId() {
        return serviceActionId;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
