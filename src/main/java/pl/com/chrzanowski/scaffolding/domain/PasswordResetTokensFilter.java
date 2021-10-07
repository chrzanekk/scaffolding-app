package pl.com.chrzanowski.scaffolding.domain;

public class PasswordResetTokensFilter {
    private String value;

    public PasswordResetTokensFilter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
