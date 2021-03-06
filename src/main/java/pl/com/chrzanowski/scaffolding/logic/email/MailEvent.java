package pl.com.chrzanowski.scaffolding.logic.email;

public enum MailEvent {
    AFTER_REGISTRATION("r"),
    AFTER_ORDER("o"),
    AFTER_PASSWORD_CHANGE("p"),
    PASSWORD_RESET("n"),
    NEWSLETTER("e"),
    EMAIL_CONFIRMATION_LINK("c");

    private String code;

    MailEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
