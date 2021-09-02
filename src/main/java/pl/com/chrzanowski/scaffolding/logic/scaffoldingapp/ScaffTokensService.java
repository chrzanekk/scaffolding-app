package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffTokenData;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ScaffTokensService {

    private final static Long TOKEN_VALIDITY_TIME_IN_HOURS = 24L;

    private ScaffTokensJdbcRepository scaffTokensJdbcRepository;

    public ScaffTokensService(ScaffTokensJdbcRepository scaffTokensJdbcRepository) {
        this.scaffTokensJdbcRepository = scaffTokensJdbcRepository;
    }

    public void create(ScaffTokenData data) {
        scaffTokensJdbcRepository.create(data);
    }

    public ScaffTokenData get(String value) {
        return scaffTokensJdbcRepository.get(value);
    }

    public ScaffTokenData prepareAndCreate(ScaffUserData user) {
        ScaffTokenData scaffTokenData = prepare(user);
        create(scaffTokenData);
        return scaffTokenData;
    }

    private ScaffTokenData prepare(ScaffUserData user) {
        String value = UUID.randomUUID().toString();
        LocalDateTime expirationDatetime = LocalDateTime.now().plusHours(TOKEN_VALIDITY_TIME_IN_HOURS);
        return new ScaffTokenData(value, expirationDatetime, user);
    }
}
