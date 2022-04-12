package pl.com.chrzanowski.scaffolding.domain.workshops;

public class WorkshopServiceTypeFilter {
    private Long workshopId;
    private Long serviceActionTypeId;

    public WorkshopServiceTypeFilter(Long workshopId, Long serviceActionTypeId) {
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
    }

    public WorkshopServiceTypeFilter(Long workshopId) {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public Long getServiceActionTypeId() {
        return serviceActionTypeId;
    }
}
