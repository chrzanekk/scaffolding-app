package pl.com.chrzanowski.scaffolding.domain;

public class OrderPaymentRemoveData {
    private Long orderId;
    private Long paymentId;

    public OrderPaymentRemoveData(Long orderId, Long paymentId) {
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
