package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.TokenData;

import java.time.LocalDateTime;

@Service
public class EmailConfirmService {

    private ScaffUsersService scaffUsersService;
    private TokensService tokensService;
    private EmailService emailService;

    public EmailConfirmService(TokensService tokensService, ScaffUsersService scaffUsersService, EmailService emailService) {
        this.tokensService = tokensService;
        this.scaffUsersService = scaffUsersService;
        this.emailService = emailService;
    }

    public void confirmEmail(String tokenValue) {

        TokenData token = tokensService.get(tokenValue);

        if (token.getExpirationDatetime().isAfter(LocalDateTime.now())) {
            scaffUsersService.update(new UserData(token.getUser(), true));
        } else {
            throw new IllegalArgumentException("Token expired");
        }

    }

    public void sendEmailConfirmationLink() {
        UserData loggedUser = scaffUsersService.getLoggedUser();
        emailService.sendEmailConfirmationLink(loggedUser);
    }


}
