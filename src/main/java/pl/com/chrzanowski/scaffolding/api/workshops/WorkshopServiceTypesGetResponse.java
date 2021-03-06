package pl.com.chrzanowski.scaffolding.api.workshops;

public class WorkshopServiceTypesGetResponse {

    private Long id;
    private Long workshopId;
    private Long serviceActionId;
    private String serviceActionName;

    public WorkshopServiceTypesGetResponse(Long id, Long workshopId, Long serviceActionId, String serviceActionName) {
        this.id = id;
        this.workshopId = workshopId;
        this.serviceActionId = serviceActionId;
        this.serviceActionName = serviceActionName;
    }

    public Long getId() {
        return id;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public Long getServiceActionId() {
        return serviceActionId;
    }

    public String getServiceActionName() {
        return serviceActionName;
    }
}
