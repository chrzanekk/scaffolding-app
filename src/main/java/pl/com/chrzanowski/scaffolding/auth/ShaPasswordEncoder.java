package pl.com.chrzanowski.scaffolding.auth;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

public class ShaPasswordEncoder extends MessageDigestPasswordEncoder { //TODO upgrade to BCryptPasswordEncoder

    public ShaPasswordEncoder() {
        this(1);
    }

    public ShaPasswordEncoder(int strength) {
        super("SHA-" + strength);
    }
}
