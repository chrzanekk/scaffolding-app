package cleaning.toDelete.logic.adviser;

import org.springframework.stereotype.Service;
import cleaning.toDelete.domain.adviser.SuggestionData;

@Service
public class SuggestionService {

    private final SuggestionJdbcRepository suggestionJdbcRepository;

    public SuggestionService(SuggestionJdbcRepository suggestionJdbcRepository) {
        this.suggestionJdbcRepository = suggestionJdbcRepository;
    }

    public void create(SuggestionData data) {
        suggestionJdbcRepository.create(data);
    }

}
