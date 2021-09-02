package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications;

import java.util.HashMap;
import java.util.Map;

public enum ScaffNotificationStatus {
    SENT("s"),
    WAITING("w");

    private String code;

    ScaffNotificationStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private static final Map<String, ScaffNotificationStatus> codesWithEnums;

    static {
        codesWithEnums = new HashMap<>();
        for (ScaffNotificationStatus codeWithEnum : ScaffNotificationStatus.values()) {
            codesWithEnums.put(codeWithEnum.code, codeWithEnum);
        }
    }

    public static ScaffNotificationStatus from(String code) {
        return codesWithEnums.get(code);
    }
}
