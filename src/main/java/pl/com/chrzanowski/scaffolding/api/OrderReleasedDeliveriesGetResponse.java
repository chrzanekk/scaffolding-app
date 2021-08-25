package pl.com.chrzanowski.scaffolding.api;

import java.util.ArrayList;
import java.util.List;

public class OrderReleasedDeliveriesGetResponse {
    List<OrderReleasedDeliveryGetResponse> deliveries = new ArrayList<>();

    public OrderReleasedDeliveriesGetResponse(List<OrderReleasedDeliveryGetResponse> deliveries) {
        this.deliveries = deliveries;
    }

    public List<OrderReleasedDeliveryGetResponse> getDeliveries() {
        return deliveries;
    }
}
