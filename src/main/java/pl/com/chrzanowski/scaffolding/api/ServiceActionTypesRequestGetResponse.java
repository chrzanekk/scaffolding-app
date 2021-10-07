package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class ServiceActionTypesRequestGetResponse {

    private List<ServiceActionTypesGetResponse> serviceActionTypes;

    public ServiceActionTypesRequestGetResponse(List<ServiceActionTypesGetResponse> serviceActionTypes) {
        this.serviceActionTypes = serviceActionTypes;
    }

    public List<ServiceActionTypesGetResponse> getServiceActionTypes() {
        return serviceActionTypes;
    }
}
