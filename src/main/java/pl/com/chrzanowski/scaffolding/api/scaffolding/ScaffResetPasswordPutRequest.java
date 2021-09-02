package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffResetPasswordPutRequest {
    private String passwordHash;
    private String token;

    public ScaffResetPasswordPutRequest() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getToken() {
        return token;
    }
}
