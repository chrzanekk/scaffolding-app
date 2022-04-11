package pl.com.chrzanowski.scaffolding.logic.user;

import java.util.HashMap;
import java.util.Map;

public enum UserAuthority {
    ADMIN("ADMIN", "ROLE_ADMIN"),
    USER("USER", "ROLE_USER");


    private String code;
    private String codeWithRole;

    UserAuthority(String code, String codeWithRole) {
        this.code = code;
        this.codeWithRole = codeWithRole;
    }

    public String getCode() {
        return code;
    }

    public String getCodeWithRole() {
        return codeWithRole;
    }

    private static final Map<String, UserAuthority> authoritiesWithCodeWithRole = new HashMap<>();

    static {
        for (UserAuthority userAuthority : UserAuthority.values()) {
            authoritiesWithCodeWithRole.put(userAuthority.codeWithRole, userAuthority);
        }
    }

    public static UserAuthority findByCodeWithRole(String codeWithRole) {
        return authoritiesWithCodeWithRole.get(codeWithRole);
    }
}
