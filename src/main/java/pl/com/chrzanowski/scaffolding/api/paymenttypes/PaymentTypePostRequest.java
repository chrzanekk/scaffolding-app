package pl.com.chrzanowski.scaffolding.api.paymenttypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;
import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class PaymentTypePostRequest extends CoreFieldsResponseRequestData {

    public PaymentTypePostRequest(String name, Long createUserId) {
        super(name, createUserId);
    }

}
