package pl.com.chrzanowski.scaffolding.api.courseplatform;

import java.util.List;

public class OrdersGetResponse {
    private List<CourseOrderGetResponse> orders;

    public OrdersGetResponse(List<CourseOrderGetResponse> orders) {
        this.orders = orders;
    }

    public List<CourseOrderGetResponse> getOrders() {
        return orders;
    }
}
