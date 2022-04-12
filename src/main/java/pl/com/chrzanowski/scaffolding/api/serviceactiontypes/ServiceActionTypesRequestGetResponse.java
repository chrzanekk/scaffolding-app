package pl.com.chrzanowski.scaffolding.api.serviceactiontypes;

import java.util.List;

public class ServiceActionTypesRequestGetResponse {

    private List<ServiceActionTypeGetResponse> serviceActionTypes;

    public ServiceActionTypesRequestGetResponse(List<ServiceActionTypeGetResponse> serviceActionTypes) {
        this.serviceActionTypes = serviceActionTypes;
    }

    public List<ServiceActionTypeGetResponse> getServiceActionTypes() {
        return serviceActionTypes;
    }
}
