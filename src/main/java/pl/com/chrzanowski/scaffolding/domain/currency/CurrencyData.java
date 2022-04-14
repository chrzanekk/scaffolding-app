package pl.com.chrzanowski.scaffolding.domain.currency;

import java.time.LocalDateTime;

public class CurrencyData {

    private Long id;
    private String name;
    private String code;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;
    private Long createUserId;
    private Long modifyUserId;
    private Long removeUserId;

    public CurrencyData(Long id,
                        String name,
                        String code){
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public CurrencyData(Long id,
                        String name,
                        String code,
                        LocalDateTime createDate,
                        LocalDateTime modifyDate,
                        LocalDateTime removeDate,
                        Long createUserId,
                        Long modifyUserId,
                        Long removeUserId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.removeDate = removeDate;
        this.createUserId = createUserId;
        this.modifyUserId = modifyUserId;
        this.removeUserId = removeUserId;
    }

    public CurrencyData(String name, String code, Long createUserId) {
        this.name = name;
        this.code = code;
        this.createUserId = createUserId;
        this.createDate = LocalDateTime.now();
    }

    public CurrencyData(Long id,
                        String name,
                        String code,
                        Long modifyUserId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.modifyDate = LocalDateTime.now();
        this.modifyUserId = modifyUserId;
    }

    public CurrencyData(Long id, Long removeUserId) {
        this.id = id;
        this.removeDate = LocalDateTime.now();
        this.removeUserId = removeUserId;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public Long getRemoveUserId() {
        return removeUserId;
    }
}
