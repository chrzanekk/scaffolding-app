package pl.com.chrzanowski.scaffolding.logic.user;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.users.PasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.users.PasswordResetTokensFilter;
import pl.com.chrzanowski.scaffolding.domain.users.UserData;
import pl.com.chrzanowski.scaffolding.domain.users.UsersFilter;
import pl.com.chrzanowski.scaffolding.logic.email.EmailService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static pl.com.chrzanowski.scaffolding.logic.utils.JdbcUtil.*;

@Service
public class PasswordResetTokensService {

    private PasswordResetTokensJdbcRepository passwordResetTokensJdbcRepository;
    private UserService userService;
    private EmailService emailService;
    private ApplicationConfig applicationConfig;

    public PasswordResetTokensService(PasswordResetTokensJdbcRepository passwordResetTokensJdbcRepository,
                                      UserService userService, EmailService emailService,
                                      ApplicationConfig applicationConfig) {
        this.passwordResetTokensJdbcRepository = passwordResetTokensJdbcRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.applicationConfig = applicationConfig;
    }

    public void create(PasswordResetTokenData data) {
        passwordResetTokensJdbcRepository.create(data);
    }

    public List<PasswordResetTokenData> find(PasswordResetTokensFilter filter) {
        return getPasswordResetToken(passwordResetTokensJdbcRepository.find(filter));
    }

    public void update(PasswordResetTokenData data) {
        passwordResetTokensJdbcRepository.update(data);
    }

    public void prepareAndSendToken(String email) {
        try {
            UserData user = userService.find(new UsersFilter(email)).get(0);
            PasswordResetTokenData token = prepare(user);
            create(token);
            emailService.sendPasswordResetMail(token);
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("We can't find account with that email");
        }
    }

    public void resetPassword(String token, String newPasswordHash) {
        try {
            PasswordResetTokenData tokenData = find(new PasswordResetTokensFilter(token)).get(0);
            validateTokenBeforeReset(tokenData);
            userService.changePassword(tokenData.getUser(), newPasswordHash);
            update(new PasswordResetTokenData(tokenData, true));
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Incorrect token!");
        }
    }

    private PasswordResetTokenData prepare(UserData user) {
        return new PasswordResetTokenData(user, UUID.randomUUID().toString(),
                LocalDateTime.now().plusMinutes(applicationConfig.getPasswordResetTokenValidityTimeInMinutes()), false);
    }

    private void validateTokenBeforeReset(PasswordResetTokenData data) {
        if (LocalDateTime.now().isAfter(data.getExpirationDatetime())) {
            throw new IllegalArgumentException("Token expired");
        } else if (data.getUsed()) {
            throw new IllegalArgumentException("Token used, can't reset password");
        }
    }

    private List<PasswordResetTokenData> getPasswordResetToken(List<Map<String, Object>> data) {

        List<PasswordResetTokenData> list = new ArrayList<>();

        for (Map<String, Object> row : data) {
            list.add(new PasswordResetTokenData(
                    getLong(row, "id"),
                    userService.find(new UsersFilter(getLong(row, "user_id"))).get(0),
                    getString(row, "value"),
                    getDateTime(row, "expiration_datetime"),
                    getBoolean(row, "used")
            ));
        }
        return list;
    }
}
