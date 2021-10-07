package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class ServiceActionsRequestGetResponse {

    private List<ServiceActionGetResponse> serviceActionGetResponseList;

    public ServiceActionsRequestGetResponse(List<ServiceActionGetResponse> serviceActionGetResponseList) {
        this.serviceActionGetResponseList = serviceActionGetResponseList;
    }

    public List<ServiceActionGetResponse> getScaffServiceActionGetResponseList() {
        return serviceActionGetResponseList;
    }
}
