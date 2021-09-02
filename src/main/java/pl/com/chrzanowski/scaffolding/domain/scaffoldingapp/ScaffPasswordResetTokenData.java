package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;



import java.time.LocalDateTime;

public class ScaffPasswordResetTokenData {
    private Long id;
    private ScaffUserData user;
    private String value;
    private LocalDateTime expirationDatetime;
    private Boolean used;

    public ScaffPasswordResetTokenData(Long id, ScaffUserData user, String value, LocalDateTime expirationDatetime, Boolean used) {
        this.id = id;
        this.user = user;
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.used = used;
    }

    public ScaffPasswordResetTokenData(ScaffUserData user, String value, LocalDateTime expirationDatetime, Boolean used) {
        this.user = user;
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.used = used;
    }

    public ScaffPasswordResetTokenData(ScaffPasswordResetTokenData data, Boolean used) {
        this.id = data.getId();
        this.user = data.getUser();
        this.value = data.getValue();
        this.expirationDatetime = data.getExpirationDatetime();
        this.used = used;
    }

    public Long getId() {
        return id;
    }

    public ScaffUserData getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getExpirationDatetime() {
        return expirationDatetime;
    }

    public Boolean getUsed() {
        return used;
    }
}
