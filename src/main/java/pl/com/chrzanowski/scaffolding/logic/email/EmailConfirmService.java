package pl.com.chrzanowski.scaffolding.logic.email;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.TokenData;
import pl.com.chrzanowski.scaffolding.logic.user.TokensService;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;

import java.time.LocalDateTime;

@Service
public class EmailConfirmService {

    private UserService userService;
    private TokensService tokensService;
    private EmailService emailService;

    public EmailConfirmService(TokensService tokensService, UserService userService, EmailService emailService) {
        this.tokensService = tokensService;
        this.userService = userService;
        this.emailService = emailService;
    }

    public void confirmEmail(String tokenValue) {

        TokenData token = tokensService.get(tokenValue);

        if (token.getExpirationDatetime().isAfter(LocalDateTime.now())) {
            userService.update(new UserData(token.getUser(), true));
        } else {
            throw new IllegalArgumentException("Token expired");
        }

    }

    public void sendEmailConfirmationLink() {
        UserData loggedUser = userService.getLoggedUser();
        emailService.sendEmailConfirmationLink(loggedUser);
    }


}
