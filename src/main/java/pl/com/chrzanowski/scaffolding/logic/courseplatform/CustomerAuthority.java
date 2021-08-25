package pl.com.chrzanowski.scaffolding.logic.courseplatform;

import java.util.HashMap;
import java.util.Map;

public enum CustomerAuthority {
    ADMIN("ADMIN", "ROLE_ADMIN"),
    USER("USER", "ROLE_USER"),
    TEACHER("TEACHER", "ROLE_TEACHER"),
    EMPLOYER("EMPLOYER", "ROLE_EMPLOYER"),
    STUDENT("STUDENT", "ROLE_STUDENT");

    private String code;
    private String codeWithRole;

    CustomerAuthority(String code, String codeWithRole) {
        this.code = code;
        this.codeWithRole = codeWithRole;
    }

    public String getCode() {
        return code;
    }

    public String getCodeWithRole() {
        return codeWithRole;
    }

    private static final Map<String, CustomerAuthority> authoritiesWithCodeWithRole = new HashMap<>();

    static {
        for (CustomerAuthority customerAuthority : CustomerAuthority.values()) {
            authoritiesWithCodeWithRole.put(customerAuthority.codeWithRole, customerAuthority);
        }
    }

    public static CustomerAuthority findByCodeWithRole(String codeWithRole) {
        return authoritiesWithCodeWithRole.get(codeWithRole);
    }
}
