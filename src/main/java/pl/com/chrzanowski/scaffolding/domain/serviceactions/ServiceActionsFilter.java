package pl.com.chrzanowski.scaffolding.domain.serviceactions;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ServiceActionsFilter {

    private Long id;
    private Long vehicleId;
    private LocalDate serviceDate;
    private String actionTypeName;
    private Long serviceActionTypeId;
    private Long workshopId;
    private String workshopName;
    private Long page;
    private Long pageSize;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Boolean itContainsRemoveDate;


    public ServiceActionsFilter(Long vehicleId,
                                Long page,
                                Long pageSize,
                                Boolean itContainsRemoveDate) {
        this.vehicleId = vehicleId;
        this.page = page;
        this.pageSize = pageSize;
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public ServiceActionsFilter(Long vehicleId,
                                String actionTypeName,
                                String workshopName,
                                LocalDate dateFrom,
                                LocalDate dateTo,
                                Long page,
                                Long pageSize,
                                Boolean itContainsRemoveDate) {
        this.vehicleId = vehicleId;
        this.actionTypeName = actionTypeName;
        this.workshopName = workshopName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.page = page;
        this.pageSize = pageSize;
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public ServiceActionsFilter(Long id) {
        this.id = id;
    }

    public ServiceActionsFilter(Long id, @NotNull Boolean itContainsRemoveDate) {
        this.id = id;
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public ServiceActionsFilter(Long vehicleId, String actionTypeName, String workshopName, LocalDate dateFrom,
                                LocalDate dateTo, @NotNull Boolean itContainsRemoveDate) {
        this.vehicleId = vehicleId;
        this.actionTypeName = actionTypeName;
        this.workshopName = workshopName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public ServiceActionsFilter(Long vehicleId, Long serviceActionTypeId, @NotNull Boolean itContainsRemoveDate) {
        this.vehicleId = vehicleId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.itContainsRemoveDate = itContainsRemoveDate;
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

    public Long getServiceActionTypeId() {
        return serviceActionTypeId;
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

    public Boolean getItContainsRemoveDate() {
        return itContainsRemoveDate;
    }
}
