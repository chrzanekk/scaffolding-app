package pl.com.chrzanowski.scaffolding.domain.currency;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

public class CurrencyData extends CoreFieldsData {
    private String code;

    public CurrencyData(Long id,
                        String name,
                        LocalDateTime createDate,
                        LocalDateTime modifyDate,
                        LocalDateTime removeDate,
                        Long createUserId,
                        Long modifyUserId,
                        Long removeUserId,
                        String code) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
        this.code = code;
    }

    public CurrencyData(String name, Long createUserId, String code) {
        super(name, createUserId);
        this.code = code;
    }

    public CurrencyData(Long id, String name, Long modifyUserId, String code) {
        super(id, name, modifyUserId);
        this.code = code;
    }

    public CurrencyData(Long id, Long removeUserId, String name, String code) {
        super(id, removeUserId, name);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
