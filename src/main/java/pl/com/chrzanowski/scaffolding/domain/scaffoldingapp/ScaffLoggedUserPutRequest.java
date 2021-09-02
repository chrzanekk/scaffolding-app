package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffLoggedUserPutRequest {
    private String language;
    private Boolean newsletterAccepted;

    public ScaffLoggedUserPutRequest() {
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getNewsletterAccepted() {
        return newsletterAccepted;
    }
}
