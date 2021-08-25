package pl.com.chrzanowski.scaffolding.auth;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
