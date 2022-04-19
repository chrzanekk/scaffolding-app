package pl.com.chrzanowski.scaffolding.api.currency;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;

public class CurrencyGetResponse extends CoreFieldsResponseRequestData {
    private String code;

    public CurrencyGetResponse(Long id,
                               String name,
                               String createDate,
                               String modifyDate,
                               String removeDate,
                               Long createUserId,
                               Long modifyUserId,
                               Long removeUserId,
                               String createUserName,
                               String modifyUserName,
                               String removeUserName,
                               String code) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId, createUserName, modifyUserName, removeUserName);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
