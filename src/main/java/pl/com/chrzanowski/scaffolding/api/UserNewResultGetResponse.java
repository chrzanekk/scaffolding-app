package pl.com.chrzanowski.scaffolding.api;

import java.util.List;

public class UserNewResultGetResponse {
    private Long count;
    private List<UserNewGetResponse> newCustomers;

    public UserNewResultGetResponse(Long count, List<UserNewGetResponse> newCustomers) {
        this.count = count;
        this.newCustomers = newCustomers;
    }

    public Long getCount() {
        return count;
    }

    public List<UserNewGetResponse> getNewCustomers() {
        return newCustomers;
    }
}
