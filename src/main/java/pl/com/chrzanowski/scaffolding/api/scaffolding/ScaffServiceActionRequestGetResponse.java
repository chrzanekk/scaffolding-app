package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffServiceActionRequestGetResponse {

    private ScaffServiceActionGetResponse serviceActionGetResponse;

    public ScaffServiceActionRequestGetResponse(ScaffServiceActionGetResponse serviceActionGetResponse) {
        this.serviceActionGetResponse = serviceActionGetResponse;
    }

    public ScaffServiceActionGetResponse getServiceActionGetResponse() {
        return serviceActionGetResponse;
    }
}
