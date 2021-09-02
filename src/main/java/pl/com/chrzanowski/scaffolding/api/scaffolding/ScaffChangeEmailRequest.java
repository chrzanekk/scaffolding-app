package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffChangeEmailRequest {

    private String passwordHash;
    private String newEmail;

    public ScaffChangeEmailRequest() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
