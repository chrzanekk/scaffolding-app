package cleaning.toDelete.api.courseplatform;

public class ResetPasswordPutRequest {
    private String passwordHash;
    private String token;

    public ResetPasswordPutRequest() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getToken() {
        return token;
    }
}
