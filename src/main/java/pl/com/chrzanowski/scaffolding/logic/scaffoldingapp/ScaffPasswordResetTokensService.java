package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.config.ApplicationConfig;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffPasswordResetTokenData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffPasswordResetTokensFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ScaffPasswordResetTokensService {

    private ScaffPasswordResetTokensJdbcRepository scaffPasswordResetTokensJdbcRepository;
    private ScaffUsersService scaffUsersService;
    private ScaffEmailService scaffEmailService;
    private ApplicationConfig applicationConfig;

    public ScaffPasswordResetTokensService(ScaffPasswordResetTokensJdbcRepository scaffPasswordResetTokensJdbcRepository,
                                           ScaffUsersService scaffUsersService, ScaffEmailService scaffEmailService,
                                           ApplicationConfig applicationConfig) {
        this.scaffPasswordResetTokensJdbcRepository = scaffPasswordResetTokensJdbcRepository;
        this.scaffUsersService = scaffUsersService;
        this.scaffEmailService = scaffEmailService;
        this.applicationConfig = applicationConfig;
    }

    public void create(ScaffPasswordResetTokenData data) {
        scaffPasswordResetTokensJdbcRepository.create(data);
    }

    public List<ScaffPasswordResetTokenData> find(ScaffPasswordResetTokensFilter filter) {
        return scaffPasswordResetTokensJdbcRepository.find(filter);
    }

    public void update(ScaffPasswordResetTokenData data) {
        scaffPasswordResetTokensJdbcRepository.update(data);
    }

    public void prepareAndSendToken(String email) {
        try {
            ScaffUserData user = scaffUsersService.find(new ScaffUsersFilter(email)).get(0);
            ScaffPasswordResetTokenData token = prepare(user);
            create(token);
            scaffEmailService.sendPasswordResetMail(token);
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("We can't find account with that email");
        }
    }

    public void resetPassword(String token, String newPasswordHash) {
        try {
            ScaffPasswordResetTokenData tokenData = find(new ScaffPasswordResetTokensFilter(token)).get(0);
            validateTokenBeforeReset(tokenData);
            scaffUsersService.changePassword(tokenData.getUser(), newPasswordHash);
            update(new ScaffPasswordResetTokenData(tokenData, true));
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Incorrect token!");
        }
    }

    private ScaffPasswordResetTokenData prepare(ScaffUserData user) {
        return new ScaffPasswordResetTokenData(user, UUID.randomUUID().toString(),
                LocalDateTime.now().plusMinutes(applicationConfig.getPasswordResetTokenValidityTimeInMinutes()), false);
    }

    private void validateTokenBeforeReset(ScaffPasswordResetTokenData data) {
        if (LocalDateTime.now().isAfter(data.getExpirationDatetime())) {
            throw new IllegalArgumentException("Token expired");
        } else if (data.getUsed()) {
            throw new IllegalArgumentException("Token used, can't reset password");
        }
    }
}
