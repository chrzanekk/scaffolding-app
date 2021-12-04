package pl.com.chrzanowski.scaffolding.domain;

import pl.com.chrzanowski.scaffolding.logic.WebUtil;

import javax.servlet.http.HttpServletRequest;
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
//to find
    public UserData(Long id, String firstName, String secondName, String login, String passwordHash, String language,
                    Boolean regulationAccepted,
                    Boolean newsletterAccepted, Boolean isEnabled, LocalDateTime registrationDatetime, String registrationIp, String registrationUserAgent, Boolean emailConfirmed) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
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

    public UserData(String login, String passwordHash, String language, Boolean regulationAccepted,
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

    public UserData(UserData userData, LocalDateTime deleteDateTime) {
        this.id = userData.getId();
        this.login = userData.getLogin();
        this.passwordHash = userData.getPasswordHash();
        this.language = userData.getLanguage();
        this.regulationAccepted = userData.getRegulationAccepted();
        this.newsletterAccepted = userData.getNewsletterAccepted();
        this.isEnabled = userData.getEnabled();
        this.emailConfirmed = userData.getEmailConfirmed();
        this.registrationDatetime = userData.getRegistrationDatetime();
        this.registrationIp = userData.getRegistrationIp();
        this.registrationUserAgent = userData.getRegistrationUserAgent();
        this.deleteDateTime = deleteDateTime;

    }

    public UserData(UserData userData, String[] authorities) {
        this.id = userData.getId();
        this.firstName = userData.getFirstName();
        this.secondName = userData.getSecondName();
        this.login = userData.getLogin();
        this.passwordHash = userData.getPasswordHash();
        this.language = userData.getLanguage();
        this.regulationAccepted = userData.getRegulationAccepted();
        this.newsletterAccepted = userData.getNewsletterAccepted();
        this.isEnabled = userData.getEnabled();
        this.emailConfirmed = userData.getEmailConfirmed();
        this.registrationDatetime = userData.getRegistrationDatetime();
        this.registrationIp = userData.getRegistrationIp();
        this.registrationUserAgent = userData.getRegistrationUserAgent();
        this.authorities = authorities;
    }

    public UserData(UserData userData, String passwordHash) {
        this.id = userData.getId();
        this.login = userData.getLogin();
        this.passwordHash = passwordHash;
        this.language = userData.getLanguage();
        this.regulationAccepted = userData.getRegulationAccepted();
        this.newsletterAccepted = userData.getNewsletterAccepted();
        this.isEnabled = userData.getEnabled();
        this.emailConfirmed = userData.getEmailConfirmed();
        this.registrationDatetime = userData.getRegistrationDatetime();
        this.registrationIp = userData.getRegistrationIp();
        this.registrationUserAgent = userData.getRegistrationUserAgent();
        this.authorities = userData.getAuthorities();
    }

    public UserData(UserData userData, String registrationIp, String registrationUserAgent) {
        this.id = userData.getId();
        this.login = userData.getLogin();
        this.passwordHash = userData.getPasswordHash();
        this.language = userData.getLanguage();
        this.regulationAccepted = userData.getRegulationAccepted();
        this.newsletterAccepted = userData.getNewsletterAccepted();
        this.isEnabled = userData.getEnabled();
        this.emailConfirmed = userData.getEmailConfirmed();
        this.registrationDatetime = userData.getRegistrationDatetime();
        this.registrationIp = registrationIp;
        this.registrationUserAgent = registrationUserAgent;
        this.authorities = userData.getAuthorities();
    }

    public UserData(UserData userData, String language, Boolean newsletterAccepted) {
        this.id = userData.getId();
        this.login = userData.getLogin();
        this.passwordHash = userData.getPasswordHash();
        this.language = language;
        this.regulationAccepted = userData.getRegulationAccepted();
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = userData.getEnabled();
        this.emailConfirmed = userData.getEmailConfirmed();
        this.registrationDatetime = userData.getRegistrationDatetime();
        this.registrationIp = userData.getRegistrationIp();
        this.registrationUserAgent = userData.getRegistrationUserAgent();
        this.authorities = userData.getAuthorities();
    }

    public UserData(UserData data, Boolean emailConfirmed) {
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

    public UserData(String login, String passwordHash, String language, Boolean regulationAccepted,
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

    public UserData(String login, String passwordHash, String language, Boolean regulationAccepted,
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

    public UserData(String login, String passwordHash, String language, Boolean regulationAccepted,
                    Boolean newsletterAccepted, Boolean isEnabled, Boolean emailConfirmed, String[] authorities, HttpServletRequest httpServletRequest) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.emailConfirmed = emailConfirmed;
        this.registrationIp = WebUtil.getClientIp(httpServletRequest);
        this.registrationUserAgent = WebUtil.getUserAgent(httpServletRequest);
        this.authorities = authorities;
    }
//to update
    public UserData(Long id, String firstName, String secondName, String login, String passwordHash, String language,
                    Boolean regulationAccepted,
                    Boolean newsletterAccepted, Boolean isEnabled, Boolean emailConfirmed,
                    LocalDateTime registrationDatetime, String[] authorities, String registrationIp, String registrationUserAgent) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
        this.regulationAccepted = regulationAccepted;
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.emailConfirmed = emailConfirmed;
        this.registrationDatetime = registrationDatetime;
        this.authorities = authorities;
        this.registrationIp = registrationIp;
        this.registrationUserAgent = registrationUserAgent;
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
