package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class ServiceActionsRequestGetResponse {

    private List<ServiceActionGetResponse> actions;

    public ServiceActionsRequestGetResponse(List<ServiceActionGetResponse> actions) {
        this.actions = actions;
    }

    public List<ServiceActionGetResponse> getActions() {
        return actions;
    }
}
