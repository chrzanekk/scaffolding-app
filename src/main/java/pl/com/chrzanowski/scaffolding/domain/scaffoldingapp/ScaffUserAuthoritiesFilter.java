package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffUserAuthoritiesFilter {
    private Long userId;
    private String authority;

    public ScaffUserAuthoritiesFilter(Long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public ScaffUserAuthoritiesFilter(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAuthority() {
        return authority;
    }
}
