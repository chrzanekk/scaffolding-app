package pl.com.chrzanowski.scaffolding.api.users;

import java.time.LocalDateTime;

public class UserPutRequest {

    private String login;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String language;
    private Boolean regulationAccepted;
    private Boolean newsletterAccepted;
    private Boolean isEnabled;
    private LocalDateTime registrationDatetime;
    private Boolean isEmailConfirmed;
    private String[] authorities;

    public UserPutRequest() {
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public Boolean getEmailConfirmed() {
        return isEmailConfirmed;
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

    public LocalDateTime getRegistrationDatetime() {
        return registrationDatetime;
    }

    public Boolean getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
