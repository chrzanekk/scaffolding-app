package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class UsersResultGetResponse {
    private Long count;
    private List<UsersGetResponse> customers;

    public UsersResultGetResponse(Long count, List<UsersGetResponse> customers) {
        this.count = count;
        this.customers = customers;
    }

    public Long getCount() {
        return count;
    }

    public List<UsersGetResponse> getCustomers() {
        return customers;
    }
}
