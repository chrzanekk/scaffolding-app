package pl.com.chrzanowski.scaffolding.api.currency;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class CurrencyPostRequest extends CoreFieldsResponseRequestData {

    private String code;

    public CurrencyPostRequest(String name, Long createUserId, String code) {
        super(name, createUserId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
