package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffServiceActionTypesRequestGetResponse {

    private List<ScaffServiceActionTypesGetResponse> serviceActionTypes;

    public ScaffServiceActionTypesRequestGetResponse(List<ScaffServiceActionTypesGetResponse> serviceActionTypes) {
        this.serviceActionTypes = serviceActionTypes;
    }

    public List<ScaffServiceActionTypesGetResponse> getServiceActionTypes() {
        return serviceActionTypes;
    }
}
