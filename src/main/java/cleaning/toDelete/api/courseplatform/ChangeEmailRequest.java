package cleaning.toDelete.api.courseplatform;

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
