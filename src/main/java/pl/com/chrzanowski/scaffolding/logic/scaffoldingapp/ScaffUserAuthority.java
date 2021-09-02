package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import java.util.HashMap;
import java.util.Map;

public enum ScaffUserAuthority {
    ADMIN("ADMIN", "ROLE_ADMIN"),
    USER("USER", "ROLE_USER");


    private String code;
    private String codeWithRole;

    ScaffUserAuthority(String code, String codeWithRole) {
        this.code = code;
        this.codeWithRole = codeWithRole;
    }

    public String getCode() {
        return code;
    }

    public String getCodeWithRole() {
        return codeWithRole;
    }

    private static final Map<String, ScaffUserAuthority> authoritiesWithCodeWithRole = new HashMap<>();

    static {
        for (ScaffUserAuthority scaffUserAuthority : ScaffUserAuthority.values()) {
            authoritiesWithCodeWithRole.put(scaffUserAuthority.codeWithRole, scaffUserAuthority);
        }
    }

    public static ScaffUserAuthority findByCodeWithRole(String codeWithRole) {
        return authoritiesWithCodeWithRole.get(codeWithRole);
    }
}
