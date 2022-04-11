package pl.com.chrzanowski.scaffolding.api.users;

public class ChangeEmailRequest {

    private String passwordHash;
    private String newEmail;

    public ChangeEmailRequest() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
