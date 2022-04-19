package pl.com.chrzanowski.scaffolding.domain.paymenttypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

public class PaymentTypeData extends CoreFieldsData {

    public PaymentTypeData(Long id,
                           String name,
                           LocalDateTime createDate,
                           LocalDateTime modifyDate,
                           LocalDateTime removeDate,
                           Long createUserId,
                           Long modifyUserId,
                           Long removeUserId) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
    }

    public PaymentTypeData(String name, Long createUserId) {
        super(name, createUserId);
    }

    public PaymentTypeData(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }

    public PaymentTypeData(Long id, Long removeUserId, String name) {
        super(id, removeUserId, name);
    }
}
