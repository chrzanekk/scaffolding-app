package pl.com.chrzanowski.scaffolding.logic.paymenttypes;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeData;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.IPaymentTypes;
import pl.com.chrzanowski.scaffolding.logic.utils.DataValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class PaymentTypeService implements IPaymentTypes {

    private PaymentTypeJdbcRepository paymentTypeJdbcRepository;

    public PaymentTypeService(PaymentTypeJdbcRepository paymentTypeJdbcRepository) {
        this.paymentTypeJdbcRepository = paymentTypeJdbcRepository;
    }

    @Override
    public Long add(PaymentTypeData data) {
        validateData(data);
        return paymentTypeJdbcRepository.create(data);
    }

    @Override
    public void update(PaymentTypeData data) {
        validateData(data);
        paymentTypeJdbcRepository.update(data);
    }

    @Override
    public List<PaymentTypeData> find(PaymentTypeFilter filter) {
        return getPaymentTypes(paymentTypeJdbcRepository.find(filter));
    }

    @Override
    public void remove(PaymentTypeData data) {
        paymentTypeJdbcRepository.remove(data);
    }

    private List<PaymentTypeData> getPaymentTypes(List<Map<String, Object>> data) {
        List<PaymentTypeData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new PaymentTypeData(
                    getLong(row, "id"),
                    getString(row, "name"),
                    getDateTime(row, "create_date"),
                    getDateTime(row, "modify_date"),
                    getDateTime(row, "remove_date"),
                    getLong(row, "create_user_id"),
                    getLong(row, "modify_user_id"),
                    getLong(row, "remove_user_id")
            ));
        }
        return list;
    }

    private void validateData(PaymentTypeData data) {
        DataValidationUtil.validateTextField(data.getName(), "Rodzaj płatności");
    }
}
