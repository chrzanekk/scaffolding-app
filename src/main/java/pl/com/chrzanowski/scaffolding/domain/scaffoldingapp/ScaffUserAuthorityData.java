package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffUserAuthorityData {

    private Long id;
    private ScaffUserData user;
    private String authority;
    private String[] authorities;

    public ScaffUserAuthorityData(Long id, ScaffUserData user, String authority) {
        this.id = id;
        this.user = user;
        this.authority = authority;
    }

    public ScaffUserAuthorityData(ScaffUserData user, String[] authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public ScaffUserData getUser() {
        return user;
    }

    public String getAuthority() {
        return authority;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
