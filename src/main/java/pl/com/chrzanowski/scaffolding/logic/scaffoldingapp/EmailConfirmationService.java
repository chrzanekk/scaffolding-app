package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.TokenData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserData;

import java.time.LocalDateTime;

@Service
public class EmailConfirmationService {

    private UsersService usersService;
    private TokensService tokensService;
    private EmailService emailService;

    public EmailConfirmationService(TokensService tokensService, UsersService usersService, EmailService emailService) {
        this.tokensService = tokensService;
        this.usersService = usersService;
        this.emailService = emailService;
    }

    public void confirmEmail(String tokenValue) {

        TokenData token = tokensService.get(tokenValue);

        if (token.getExpirationDatetime().isAfter(LocalDateTime.now())) {
            usersService.update(new UserData(token.getUser(), true));
        } else {
            throw new IllegalArgumentException("Token expired");
        }

    }

    public void sendEmailConfirmationLink() {
        UserData loggedUser = usersService.getLoggedUser();
        emailService.sendEmailConfirmationLink(loggedUser);
    }


}
