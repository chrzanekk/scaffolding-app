package cleaning.toDelete.api.lifeadviser;

public class PurchasePostRequest {


    private String applicationId;
    private String domain;
    private String domainId;
    private String secret;
    private Long categoryId;
    private String period;

    public PurchasePostRequest() {
    }

    public PurchasePostRequest(String applicationId, String domain, String domainId, String secret, Long categoryId, String period) {
        this.applicationId = applicationId;
        this.domain = domain;
        this.domainId = domainId;
        this.secret = secret;
        this.categoryId = categoryId;
        this.period = period;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getDomain() {
        return domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getSecret() {
        return secret;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getPeriod() {
        return period;
    }
}
