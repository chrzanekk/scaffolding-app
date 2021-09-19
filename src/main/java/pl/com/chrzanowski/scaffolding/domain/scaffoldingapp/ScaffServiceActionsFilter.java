package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import java.time.LocalDate;

public class ScaffServiceActionsFilter {

    private Long id;
    private Long vehicleId;
    private LocalDate serviceDate;
    private String actionType;
    private Long page;
    private Long pageSize;

    public ScaffServiceActionsFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ScaffServiceActionsFilter(Long vehicleId, Long page, Long pageSize) {
        this.vehicleId = vehicleId;
        this.page = page;
        this.pageSize = pageSize;
    }



    public ScaffServiceActionsFilter(Long id) {
        this.id = id;
    }

    public ScaffServiceActionsFilter() {
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

    public String getActionType() {
        return actionType;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
