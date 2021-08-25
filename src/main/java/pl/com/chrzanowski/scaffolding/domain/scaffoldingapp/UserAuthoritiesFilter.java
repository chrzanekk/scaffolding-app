package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class UserAuthoritiesFilter {
    private Long userId;
    private String authority;

    public UserAuthoritiesFilter(Long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public UserAuthoritiesFilter(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAuthority() {
        return authority;
    }
}
