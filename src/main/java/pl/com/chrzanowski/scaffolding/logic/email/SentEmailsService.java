package pl.com.chrzanowski.scaffolding.logic.email;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.SentEmailData;

import java.time.LocalDateTime;

@Service
public class SentEmailsService {

    private SentEmailsJdbcRepository sentEmailsJdbcRepository;

    public SentEmailsService(SentEmailsJdbcRepository sentEmailsJdbcRepository) {
        this.sentEmailsJdbcRepository = sentEmailsJdbcRepository;
    }

    public void create(SentEmailData data) {
        sentEmailsJdbcRepository.create(new SentEmailData(data, LocalDateTime.now()));
    }
}
