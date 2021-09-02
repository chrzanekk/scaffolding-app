package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffSentEmailData;

import java.time.LocalDateTime;

@Service
public class ScaffSentEmailsService {

    private ScaffSentEmailsJdbcRepository scaffSentEmailsJdbcRepository;

    public ScaffSentEmailsService(ScaffSentEmailsJdbcRepository scaffSentEmailsJdbcRepository) {
        this.scaffSentEmailsJdbcRepository = scaffSentEmailsJdbcRepository;
    }

    public void create(ScaffSentEmailData data) {
        scaffSentEmailsJdbcRepository.create(new ScaffSentEmailData(data, LocalDateTime.now()));
    }
}
