package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDate;

public class ServiceActionsFilter {

    private Long id;
    private Long vehicleId;
    private LocalDate serviceDate;
    private String actionTypeName;
    private Long serviceActionId;
    private Long workshopId;
    private Long page;
    private Long pageSize;


    public ServiceActionsFilter(Long vehicleId, Long page, Long pageSize) {
        this.vehicleId = vehicleId;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ServiceActionsFilter(Long id) {
        this.id = id;
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

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
