package pl.com.chrzanowski.scaffolding.api.currency;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class CurrencyPutRequest extends CoreFieldsResponseRequestData {

    private String code;

    public CurrencyPutRequest(Long id, String name, Long modifyUserId, String code) {
        super(id, name, modifyUserId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
