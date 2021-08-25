package pl.com.chrzanowski.scaffolding.api;

import java.util.ArrayList;
import java.util.List;

public class OrderEmailResultGetResponse {
    List<OrderEmailGetResponse> emails = new ArrayList<>();

    public OrderEmailResultGetResponse(List<OrderEmailGetResponse> emails) {
        this.emails = emails;
    }

    public List<OrderEmailGetResponse> getEmails() {
        return emails;
    }
}
