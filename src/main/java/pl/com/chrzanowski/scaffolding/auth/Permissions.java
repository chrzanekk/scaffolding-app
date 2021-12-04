package pl.com.chrzanowski.scaffolding.auth;

public enum Permissions {

    ADMIN("admin"),
    USER("user");

    private String code;

    Permissions(String code) {
        this.code = code;
    }

    String code() {
        return this.code;
    }
}
