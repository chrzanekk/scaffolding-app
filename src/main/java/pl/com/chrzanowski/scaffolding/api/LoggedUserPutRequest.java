package pl.com.chrzanowski.scaffolding.api;

public class LoggedUserPutRequest {
    private String language;
    private Boolean newsletterAccepted;

    public LoggedUserPutRequest() {
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getNewsletterAccepted() {
        return newsletterAccepted;
    }
}
