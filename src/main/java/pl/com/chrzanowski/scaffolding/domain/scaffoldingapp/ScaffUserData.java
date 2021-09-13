package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import pl.com.chrzanowski.scaffolding.logic.courseplatform.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ScaffUserData {
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

    public ScaffUserData(Long id, String login, String passwordHash, String firstName, String secondName,
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

    public ScaffUserData(Long id, String login, String passwordHash, String language, Boolean regulationAccepted, Boolean newsletterAccepted, Boolean isEnabled, LocalDateTime registrationDatetime, String registrationIp, String registrationUserAgent, Boolean emailConfirmed) {
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

    public ScaffUserData(String login, String passwordHash, String language, Boolean regulationAccepted,
                         Boolean newsletterAccepted, Boolean isEnabled, LocalDateTime registrationDatetime,
                         String registrationIp, String registrationUserAgent, Boolean emailConfirmed) {
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

    public ScaffUserData(ScaffUserData scaffUserData, LocalDateTime deleteDateTime) {
        this.id = scaffUserData.getId();
        this.login = scaffUserData.getLogin();
        this.passwordHash = scaffUserData.getPasswordHash();
        this.language = scaffUserData.getLanguage();
        this.regulationAccepted = scaffUserData.getRegulationAccepted();
        this.newsletterAccepted = scaffUserData.getNewsletterAccepted();
        this.isEnabled = scaffUserData.getEnabled();
        this.emailConfirmed = scaffUserData.getEmailConfirmed();
        this.registrationDatetime = scaffUserData.getRegistrationDatetime();
        this.registrationIp = scaffUserData.getRegistrationIp();
        this.registrationUserAgent = scaffUserData.getRegistrationUserAgent();
        this.deleteDateTime = deleteDateTime;

    }

    public ScaffUserData(ScaffUserData scaffUserData, String[] authorities) {
        this.id = scaffUserData.getId();
        this.login = scaffUserData.getLogin();
        this.passwordHash = scaffUserData.getPasswordHash();
        this.language = scaffUserData.getLanguage();
        this.regulationAccepted = scaffUserData.getRegulationAccepted();
        this.newsletterAccepted = scaffUserData.getNewsletterAccepted();
        this.isEnabled = scaffUserData.getEnabled();
        this.emailConfirmed = scaffUserData.getEmailConfirmed();
        this.registrationDatetime = scaffUserData.getRegistrationDatetime();
        this.registrationIp = scaffUserData.getRegistrationIp();
        this.registrationUserAgent = scaffUserData.getRegistrationUserAgent();
        this.authorities = authorities;
    }

    public ScaffUserData(ScaffUserData scaffUserData, String passwordHash) {
        this.id = scaffUserData.getId();
        this.login = scaffUserData.getLogin();
        this.passwordHash = passwordHash;
        this.language = scaffUserData.getLanguage();
        this.regulationAccepted = scaffUserData.getRegulationAccepted();
        this.newsletterAccepted = scaffUserData.getNewsletterAccepted();
        this.isEnabled = scaffUserData.getEnabled();
        this.emailConfirmed = scaffUserData.getEmailConfirmed();
        this.registrationDatetime = scaffUserData.getRegistrationDatetime();
        this.registrationIp = scaffUserData.getRegistrationIp();
        this.registrationUserAgent = scaffUserData.getRegistrationUserAgent();
        this.authorities = scaffUserData.getAuthorities();
    }

    public ScaffUserData(ScaffUserData scaffUserData, String registrationIp, String registrationUserAgent) {
        this.id = scaffUserData.getId();
        this.login = scaffUserData.getLogin();
        this.passwordHash = scaffUserData.getPasswordHash();
        this.language = scaffUserData.getLanguage();
        this.regulationAccepted = scaffUserData.getRegulationAccepted();
        this.newsletterAccepted = scaffUserData.getNewsletterAccepted();
        this.isEnabled = scaffUserData.getEnabled();
        this.emailConfirmed = scaffUserData.getEmailConfirmed();
        this.registrationDatetime = scaffUserData.getRegistrationDatetime();
        this.registrationIp = registrationIp;
        this.registrationUserAgent = registrationUserAgent;
        this.authorities = scaffUserData.getAuthorities();
    }

    public ScaffUserData(ScaffUserData scaffUserData, String language, Boolean newsletterAccepted) {
        this.id = scaffUserData.getId();
        this.login = scaffUserData.getLogin();
        this.passwordHash = scaffUserData.getPasswordHash();
        this.language = language;
        this.regulationAccepted = scaffUserData.getRegulationAccepted();
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = scaffUserData.getEnabled();
        this.emailConfirmed = scaffUserData.getEmailConfirmed();
        this.registrationDatetime = scaffUserData.getRegistrationDatetime();
        this.registrationIp = scaffUserData.getRegistrationIp();
        this.registrationUserAgent = scaffUserData.getRegistrationUserAgent();
        this.authorities = scaffUserData.getAuthorities();
    }

    public ScaffUserData(ScaffUserData data, Boolean emailConfirmed) {
        this.id = data.getId();
        this.login = data.getLogin();
        this.passwordHash = data.getPasswordHash();
        this.language = data.getLanguage();
        this.regulationAccepted = data.getRegulationAccepted();
        this.newsletterAccepted = data.getEmailConfirmed();
        this.isEnabled = data.getEnabled();
        this.emailConfirmed = emailConfirmed;
        this.registrationDatetime = data.getRegistrationDatetime();
        this.registrationIp = data.getRegistrationIp();
        this.registrationUserAgent = data.getRegistrationUserAgent();
        this.authorities = data.getAuthorities();
    }

    public ScaffUserData(String login, String passwordHash, String language, Boolean regulationAccepted,
                         Boolean newsletterAccepted, HttpServletRequest httpServletRequest) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.registrationDatetime = LocalDateTime.now();
        this.registrationIp = WebUtil.getClientIp(httpServletRequest);
        this.registrationUserAgent = WebUtil.getUserAgent(httpServletRequest);
    }

    public ScaffUserData(String login, String passwordHash, String language, Boolean regulationAccepted,
                         Boolean newsletterAccepted, Boolean isEnabled, Boolean emailConfirmed, HttpServletRequest httpServletRequest) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.emailConfirmed = emailConfirmed;
        this.registrationIp = WebUtil.getUserAgent(httpServletRequest);
        this.registrationUserAgent = WebUtil.getUserAgent(httpServletRequest);
    }

    public ScaffUserData(String login, String passwordHash, String language, Boolean regulationAccepted,
                         Boolean newsletterAccepted, Boolean isEnabled, Boolean emailConfirmed, String[] authorities, HttpServletRequest httpServletRequest) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.emailConfirmed = emailConfirmed;
        this.registrationIp = WebUtil.getUserAgent(httpServletRequest);
        this.registrationUserAgent = WebUtil.getUserAgent(httpServletRequest);
        this.authorities = authorities;
    }

    public ScaffUserData(Long id, String login, String passwordHash, String language, Boolean regulationAccepted, Boolean newsletterAccepted, Boolean isEnabled, Boolean emailConfirmed, LocalDateTime registrationDatetime, String[] authorities) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.emailConfirmed = emailConfirmed;
        this.registrationDatetime = registrationDatetime;
        this.authorities = authorities;
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
