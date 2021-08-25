package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import java.time.LocalDateTime;

public class UserData {
    private Long id;
    private String login;
    private String passwordHash;
    private String language;
    private Boolean regulationAccepted;
    private Boolean newsletterAccepted;
    private Boolean isEnabled;
    private LocalDateTime registrationDatetime;
    private String registrationIp;
    private String registrationUserAgent;
    private Boolean emailConfirmed;
    private LocalDateTime deleteDateTime;
    private String[] authorities;
    private String firstName;
    private String secondName;

    public UserData(Long id, String login, String passwordHash, String firstName, String secondName,
                    String language, Boolean regulationAccepted, Boolean newsletterAccepted, Boolean isEnabled, LocalDateTime registrationDatetime, String registrationIp, String registrationUserAgent, Boolean emailConfirmed, LocalDateTime deleteDateTime, String[] authorities) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.secondName = secondName;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.registrationDatetime = registrationDatetime;
        this.registrationIp = registrationIp;
        this.registrationUserAgent = registrationUserAgent;
        this.emailConfirmed = emailConfirmed;
        this.deleteDateTime = deleteDateTime;
        this.authorities = authorities;
    }

    public UserData(Long id, String login, String passwordHash, String language, Boolean regulationAccepted, Boolean newsletterAccepted, Boolean isEnabled, LocalDateTime registrationDatetime, String registrationIp, String registrationUserAgent, Boolean emailConfirmed) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.registrationDatetime = registrationDatetime;
        this.registrationIp = registrationIp;
        this.registrationUserAgent = registrationUserAgent;
        this.emailConfirmed = emailConfirmed;
    }

    public Long getId() {
        return id;
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

    public Boolean getEnabled() {
        return isEnabled;
    }

    public LocalDateTime getRegistrationDatetime() {
        return registrationDatetime;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public String getRegistrationUserAgent() {
        return registrationUserAgent;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public LocalDateTime getDeleteDateTime() {
        return deleteDateTime;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
