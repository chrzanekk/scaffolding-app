package pl.com.chrzanowski.scaffolding.api.paymenttype;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;
import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

import java.time.LocalDateTime;

public class PaymentTypeGetResponse extends CoreFieldsResponseRequestData {

    public PaymentTypeGetResponse(Long id,
                                  String name,
                                  String createDate,
                                  String modifyDate,
                                  String removeDate,
                                  Long createUserId,
                                  Long modifyUserId,
                                  Long removeUserId) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
    }
}
