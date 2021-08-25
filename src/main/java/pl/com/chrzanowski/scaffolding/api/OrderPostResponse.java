package pl.com.chrzanowski.scaffolding.api;

public class OrderPostResponse {
    private Long newOrderId;


    public OrderPostResponse(Long newOrderId) {
        this.newOrderId = newOrderId;
    }


    public Long getNewOrderId() {
        return newOrderId;
    }
}
