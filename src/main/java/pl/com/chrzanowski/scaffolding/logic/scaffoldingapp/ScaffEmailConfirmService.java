package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffTokenData;

import java.time.LocalDateTime;

@Service
public class ScaffEmailConfirmService {

    private ScaffUsersService scaffUsersService;
    private ScaffTokensService scaffTokensService;
    private ScaffEmailService scaffEmailService;

    public ScaffEmailConfirmService(ScaffTokensService scaffTokensService, ScaffUsersService scaffUsersService, ScaffEmailService scaffEmailService) {
        this.scaffTokensService = scaffTokensService;
        this.scaffUsersService = scaffUsersService;
        this.scaffEmailService = scaffEmailService;
    }

    public void confirmEmail(String tokenValue) {

        ScaffTokenData token = scaffTokensService.get(tokenValue);

        if (token.getExpirationDatetime().isAfter(LocalDateTime.now())) {
            scaffUsersService.update(new ScaffUserData(token.getUser(), true));
        } else {
            throw new IllegalArgumentException("Token expired");
        }

    }

    public void sendEmailConfirmationLink() {
        ScaffUserData loggedUser = scaffUsersService.getLoggedUser();
        scaffEmailService.sendEmailConfirmationLink(loggedUser);
    }


}
