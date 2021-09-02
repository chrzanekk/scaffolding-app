package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications;

import java.util.HashMap;
import java.util.Map;

public enum ScaffNotificationType {
    PLATFORM("p"),
    EMAIL("e");

    private String code;

    ScaffNotificationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private static final Map<String, ScaffNotificationType> codesWithEnums;

    static {
        codesWithEnums = new HashMap<>();
        for (ScaffNotificationType codeWithEnum : ScaffNotificationType.values()) {
            codesWithEnums.put(codeWithEnum.code, codeWithEnum);
        }
    }

    public static ScaffNotificationType from(String code) {
        return codesWithEnums.get(code);
    }
}
