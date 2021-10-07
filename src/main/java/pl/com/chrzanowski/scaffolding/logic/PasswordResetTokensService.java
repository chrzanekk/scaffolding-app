package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.PasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.PasswordResetTokensFilter;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PasswordResetTokensService {

    private PasswordResetTokensJdbcRepository passwordResetTokensJdbcRepository;
    private ScaffUsersService scaffUsersService;
    private EmailService emailService;
    private ApplicationConfig applicationConfig;

    public PasswordResetTokensService(PasswordResetTokensJdbcRepository passwordResetTokensJdbcRepository,
                                      ScaffUsersService scaffUsersService, EmailService emailService,
                                      ApplicationConfig applicationConfig) {
        this.passwordResetTokensJdbcRepository = passwordResetTokensJdbcRepository;
        this.scaffUsersService = scaffUsersService;
        this.emailService = emailService;
        this.applicationConfig = applicationConfig;
    }

    public void create(PasswordResetTokenData data) {
        passwordResetTokensJdbcRepository.create(data);
    }

    public List<PasswordResetTokenData> find(PasswordResetTokensFilter filter) {
        return passwordResetTokensJdbcRepository.find(filter);
    }

    public void update(PasswordResetTokenData data) {
        passwordResetTokensJdbcRepository.update(data);
    }

    public void prepareAndSendToken(String email) {
        try {
            UserData user = scaffUsersService.find(new UsersFilter(email)).get(0);
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
            scaffUsersService.changePassword(tokenData.getUser(), newPasswordHash);
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
}
