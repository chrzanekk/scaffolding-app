package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;


import java.time.LocalDateTime;

public class ScaffTokenData {
    private Long id;
    private String value;
    private LocalDateTime expirationDatetime;
    private ScaffUserData user;

    public ScaffTokenData(Long id, String value, LocalDateTime expirationDatetime, ScaffUserData user) {
        this.id = id;
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.user = user;
    }

    public ScaffTokenData(String value, LocalDateTime expirationDatetime, ScaffUserData user) {
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getExpirationDatetime() {
        return expirationDatetime;
    }

    public ScaffUserData getUser() {
        return user;
    }
}
