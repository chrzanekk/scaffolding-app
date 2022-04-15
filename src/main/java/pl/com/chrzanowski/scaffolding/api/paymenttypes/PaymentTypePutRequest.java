package pl.com.chrzanowski.scaffolding.api.paymenttypes;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

public class PaymentTypePutRequest extends CoreFieldsData {

    public PaymentTypePutRequest(Long id, String name, Long modifyUserId) {
        super(id, name, modifyUserId);
    }

    @Override
    public CoreFieldsData constructorForPutRequest(Long id, String name, Long modifyUserId) {
        return super.constructorForPutRequest(id, name, modifyUserId);
    }


}
