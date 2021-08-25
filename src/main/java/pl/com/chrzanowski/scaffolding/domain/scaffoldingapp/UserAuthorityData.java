package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerData;

public class UserAuthorityData {

    private Long id;
    private UserData user;
    private String authority;
    private String[] authorities;

    public UserAuthorityData(Long id, UserData user, String authority, String[] authorities) {
        this.id = id;
        this.user = user;
        this.authority = authority;
        this.authorities = authorities;
    }

    public UserAuthorityData(Long id, UserData user, String authority) {
        this.id = id;
        this.user = user;
        this.authority = authority;
    }

    public UserAuthorityData(UserData user, String[] authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public UserData getUser() {
        return user;
    }

    public String getAuthority() {
        return authority;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
