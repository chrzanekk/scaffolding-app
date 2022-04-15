package pl.com.chrzanowski.scaffolding.api.paymenttype;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

public class PaymentTypePostRequest extends CoreFieldsData {

    public PaymentTypePostRequest(String name, Long createUserId) {
        super(name, createUserId);
    }

    @Override
    public CoreFieldsData constructorForPostRequest(String name, Long createUserId) {
        return super.constructorForPostRequest(name, createUserId);
    }

}
