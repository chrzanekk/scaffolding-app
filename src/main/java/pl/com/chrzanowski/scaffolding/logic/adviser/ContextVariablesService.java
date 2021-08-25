package pl.com.chrzanowski.scaffolding.logic.adviser;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.adviser.ContextVariableData;
import pl.com.chrzanowski.scaffolding.domain.adviser.ContextVariablesFilter;

import java.util.List;

@Service
public class ContextVariablesService {

    private final ContextVariablesJdbcRepository contextVariablesJdbcRepository;

    public ContextVariablesService(ContextVariablesJdbcRepository contextVariablesJdbcRepository) {
        this.contextVariablesJdbcRepository = contextVariablesJdbcRepository;
    }

    public List<ContextVariableData> find(ContextVariablesFilter filter) {
        return contextVariablesJdbcRepository.find(filter);
    }

    public Long getTotalContextVariablesCount() {
        return contextVariablesJdbcRepository.getTotalContextVariablesCount();
    }

    public void create(ContextVariableData data) {
        contextVariablesJdbcRepository.create(data);
    }

    public void update(ContextVariableData data) {
        contextVariablesJdbcRepository.update(data);
    }
}
