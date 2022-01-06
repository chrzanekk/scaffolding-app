package pl.com.chrzanowski.scaffolding.api;

public class UserPostRequest {

    private String login;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String language;
    private Boolean regulationAccepted;
    private Boolean newsletterAccepted;
    private Boolean isEnabled;
    private Boolean isEmailConfirmed;
    private String[] authorities;


    public UserPostRequest() {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public Boolean getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
