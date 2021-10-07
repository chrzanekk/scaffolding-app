package pl.com.chrzanowski.scaffolding.domain;

import pl.com.chrzanowski.scaffolding.logic.Language;

public class UsersFilter {
    private Long id;
    private String login;
    private Boolean newsletterAccepted;
    private Boolean isEnabled;
    private Long limit;
    private Boolean emailConfirmed;
    private Language language;
    private Long page;
    private Long pageSize;
    private String loginLike;

    public UsersFilter(String loginLike, Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.loginLike = loginLike;
    }


    public UsersFilter(String login) {
        this.login = login;
    }

    public UsersFilter(Long id) {
        this.id = id;
    }

    public UsersFilter(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public UsersFilter(Language language) {
        this.language = language;
    }

    public UsersFilter(Boolean newsletterAccepted, Boolean isEnabled, Long limit, Boolean emailConfirmed) {
        this.newsletterAccepted = newsletterAccepted;
        this.isEnabled = isEnabled;
        this.limit = limit;
        this.emailConfirmed = emailConfirmed;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Boolean getNewsletterAccepted() {
        return newsletterAccepted;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public Long getLimit() {
        return limit;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public Language getLanguage() {
        return language;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public String getLoginLike() {
        return loginLike;
    }
}
