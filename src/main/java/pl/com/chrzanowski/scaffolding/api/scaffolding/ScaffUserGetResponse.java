package pl.com.chrzanowski.scaffolding.api.scaffolding;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;

public class ScaffUserGetResponse {
    private Long id;
    private String login;
    private String language;
    private Boolean newsletterAccepted;
    private Boolean regulationAccepted;
    private String passwordHash;
    private String registrationDatetime;
    private Boolean isEnabled;
    private String registrationIp;
    private String userAgent;
    private Boolean emailConfirmed;
    private String[] authorities;

    public ScaffUserGetResponse(ScaffUserData data) {
        this.id = data.getId();
        this.login = data.getLogin();
        this.language = data.getLanguage();
        this.newsletterAccepted = data.getNewsletterAccepted();
        this.regulationAccepted = data.getRegulationAccepted();
        this.passwordHash = data.getPasswordHash();
        this.registrationDatetime = data.getRegistrationDatetime().toString().replace("T", " ");
        this.isEnabled = data.getEnabled();
        this.registrationIp = data.getRegistrationIp();
        this.userAgent = data.getRegistrationUserAgent();
        this.emailConfirmed = data.getEmailConfirmed();
        this.authorities = data.getAuthorities();
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getNewsletterAccepted() {
        return newsletterAccepted;
    }

    public Boolean getRegulationAccepted() {
        return regulationAccepted;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRegistrationDatetime() {
        return registrationDatetime;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
