package pl.com.chrzanowski.scaffolding.logic.notifications;

import java.util.HashMap;
import java.util.Map;

public enum ScaffNotificationKind {
    EMAIL_CONFIRMATION_REMINDER("e"),
    FROM_PANEL_ADMIN("p");

    private String code;

    ScaffNotificationKind(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private static final Map<String, ScaffNotificationKind> codesWithEnums;

    static {
        codesWithEnums = new HashMap<>();
        for (ScaffNotificationKind codeWithEnum : ScaffNotificationKind.values()) {
            codesWithEnums.put(codeWithEnum.code, codeWithEnum);
        }
    }

    public static ScaffNotificationKind from(String code) {
        return codesWithEnums.get(code);
    }
}
