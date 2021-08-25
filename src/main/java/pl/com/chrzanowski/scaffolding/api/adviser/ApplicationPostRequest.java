package pl.com.chrzanowski.scaffolding.api.adviser;

public class ApplicationPostRequest {
    private String applicationId;
    private String description;
    private String secretKey;

    public ApplicationPostRequest() {
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getDescription() {
        return description;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
