package pl.com.chrzanowski.scaffolding.domain;

public class WorkshopServiceTypeData {
    private Long id;
    private WorkshopsData workshopsData;
    private ServiceActionTypeData serviceActionTypeData;
    private Long workshopId;
    private Long serviceActionTypeId;
    private Long[] serviceActionTypes;
    private String serviceActionName;

    public WorkshopServiceTypeData(Long id, WorkshopsData workshopsData, ServiceActionTypeData serviceActionTypeData, Long workshopId, Long serviceActionTypeId, Long[] serviceActionTypes) {
        this.id = id;
        this.workshopsData = workshopsData;
        this.serviceActionTypeData = serviceActionTypeData;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionTypes = serviceActionTypes;
    }

    public WorkshopServiceTypeData(Long id, Long workshopId, Long serviceActionTypeId, String serviceActionName ) {
        this.id = id;
        this.workshopId = workshopId;
        this.serviceActionTypeId = serviceActionTypeId;
        this.serviceActionName = serviceActionName;
    }

    public WorkshopServiceTypeData(Long workshopId, Long[] serviceActionTypes) {
        this.workshopId = workshopId;
        this.serviceActionTypes = serviceActionTypes;
    }

    public WorkshopServiceTypeData(Long[] serviceActionTypes) {
        this.serviceActionTypes = serviceActionTypes;
    }

    public Long getId() {
        return id;
    }

    public WorkshopsData getWorkshopsData() {
        return workshopsData;
    }

    public String getServiceActionName() {
        return serviceActionName;
    }

    public ServiceActionTypeData getServiceActionTypeData() {
        return serviceActionTypeData;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public Long getServiceActionTypeId() {
        return serviceActionTypeId;
    }

    public Long[] getServiceActionTypes() {
        return serviceActionTypes;
    }
}
