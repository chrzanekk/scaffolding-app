package pl.com.chrzanowski.scaffolding.logic.user;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.users.UserData;
import pl.com.chrzanowski.scaffolding.domain.users.TokenData;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokensService {

    private final static Long TOKEN_VALIDITY_TIME_IN_HOURS = 24L;

    private TokensJdbcRepository tokensJdbcRepository;

    public TokensService(TokensJdbcRepository tokensJdbcRepository) {
        this.tokensJdbcRepository = tokensJdbcRepository;
    }

    public void create(TokenData data) {
        tokensJdbcRepository.create(data);
    }

    public TokenData get(String value) {
        return tokensJdbcRepository.get(value);
    }

    public TokenData prepareAndCreate(UserData user) {
        TokenData tokenData = prepare(user);
        create(tokenData);
        return tokenData;
    }

    private TokenData prepare(UserData user) {
        String value = UUID.randomUUID().toString();
        LocalDateTime expirationDatetime = LocalDateTime.now().plusHours(TOKEN_VALIDITY_TIME_IN_HOURS);
        return new TokenData(value, expirationDatetime, user);
    }
}
