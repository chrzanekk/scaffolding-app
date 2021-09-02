package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffChangePasswordRequest {

    private String actualPasswordHash;
    private String newPasswordHash;

    public ScaffChangePasswordRequest() {
    }

    public String getActualPasswordHash() {
        return actualPasswordHash;
    }

    public String getNewPasswordHash() {
        return newPasswordHash;
    }
}
