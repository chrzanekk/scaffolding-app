package pl.com.chrzanowski.scaffolding.api.paymenttypes;

public class PaymentTypeRequestGetResponse {

    private PaymentTypeGetResponse paymentType;

    public PaymentTypeRequestGetResponse(PaymentTypeGetResponse paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentTypeGetResponse getPaymentType() {
        return paymentType;
    }
}
