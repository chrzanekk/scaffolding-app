package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffUserPutRequest {

    private String login;
    private String passwordHash;
    private String language;
    private Boolean regulationAccepted;
    private Boolean newsletterAccepted;
    private Boolean isEnabled;
    private String registrationDatetime;
    private Boolean isEmailConfirmed;
    private String[] authorities;

    public ScaffUserPutRequest() {
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getRegulationAccepted() {
        return regulationAccepted;
    }

    public Boolean getNewsletterAccepted() {
        return newsletterAccepted;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public String getRegistrationDatetime() {
        return registrationDatetime;
    }

    public Boolean getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
