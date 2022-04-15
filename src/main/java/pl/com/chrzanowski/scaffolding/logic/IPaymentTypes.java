package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeData;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeFilter;

import java.util.List;

public interface IPaymentTypes {

    Long add(PaymentTypeData data);
    void update(PaymentTypeData data);
    List<PaymentTypeData> find(PaymentTypeFilter filter);
    void remove(PaymentTypeData data);
}
