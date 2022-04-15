package pl.com.chrzanowski.scaffolding.api.paymenttype;

import java.util.List;

public class PaymentTypesRequestGetResponse {

    private List<PaymentTypeGetResponse> paymentTypes;

    public PaymentTypesRequestGetResponse(List<PaymentTypeGetResponse> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public List<PaymentTypeGetResponse> getPaymentTypes() {
        return paymentTypes;
    }
}
