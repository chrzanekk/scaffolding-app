package pl.com.chrzanowski.scaffolding.domain;



import java.time.LocalDateTime;

public class PasswordResetTokenData {
    private Long id;
    private UserData user;
    private String value;
    private LocalDateTime expirationDatetime;
    private Boolean used;

    public PasswordResetTokenData(Long id, UserData user, String value, LocalDateTime expirationDatetime, Boolean used) {
        this.id = id;
        this.user = user;
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.used = used;
    }

    public PasswordResetTokenData(UserData user, String value, LocalDateTime expirationDatetime, Boolean used) {
        this.user = user;
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.used = used;
    }

    public PasswordResetTokenData(PasswordResetTokenData data, Boolean used) {
        this.id = data.getId();
        this.user = data.getUser();
        this.value = data.getValue();
        this.expirationDatetime = data.getExpirationDatetime();
        this.used = used;
    }

    public Long getId() {
        return id;
    }

    public UserData getUser() {
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
