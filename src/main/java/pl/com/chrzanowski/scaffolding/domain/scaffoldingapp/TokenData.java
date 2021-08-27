package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;


import java.time.LocalDateTime;

public class TokenData {
    private Long id;
    private String value;
    private LocalDateTime expirationDatetime;
    private UserData user;

    public TokenData(Long id, String value, LocalDateTime expirationDatetime, UserData user) {
        this.id = id;
        this.value = value;
        this.expirationDatetime = expirationDatetime;
        this.user = user;
    }

    public TokenData(String value, LocalDateTime expirationDatetime, UserData user) {
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

    public UserData getUser() {
        return user;
    }
}
