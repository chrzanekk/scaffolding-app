package cleaning.toDelete.logic.adviser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cleaning.toDelete.domain.adviser.*;
import pl.com.chrzanowski.scaffolding.logic.CommonJdbcRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AdviserService {
    private AdviserJdbcRepository adviserJdbcRepository;
    private ApplicationsService applicationsService;
    private ContextVariablesService contextVariablesService;
    private CommonJdbcRepository commonJdbcRepository;

    public AdviserService(AdviserJdbcRepository adviserJdbcRepository, ApplicationsService applicationsService, ContextVariablesService contextVariablesService, CommonJdbcRepository commonJdbcRepository) {
        this.adviserJdbcRepository = adviserJdbcRepository;
        this.applicationsService = applicationsService;
        this.contextVariablesService = contextVariablesService;
        this.commonJdbcRepository = commonJdbcRepository;
    }

    public void createAdvice(AdviceData data) {
        validateAdvice(data);
        adviserJdbcRepository.createAdvice(data);
    }

    public AdviceData getAdvice(Long id) {
        return adviserJdbcRepository.getAdvice(id);
    }

    public List<AdviceData> findAdvices(AdvicesFilter filer) {
        return adviserJdbcRepository.findAdvices(filer);
    }

    public void updateAdvice(AdviceData data) {
        validateAdvice(data);
        adviserJdbcRepository.updateAdvice(data);
    }

    public void deleteAdvice(Long id) {
        adviserJdbcRepository.deleteAdvice(id);
    }

    public Long createTriggeredAdvice(TriggeredAdviceData data) {
        adviserJdbcRepository.createTriggeredAdvice(data);
        return commonJdbcRepository.getLastInsertedId();
    }

    public TriggeredAdviceData getTriggeredAdvice(Long id) {
        return adviserJdbcRepository.getTriggeredAdvice(id);
    }

    public List<TriggeredAdviceData> findTriggeredAdvices(TriggeredAdvicesFilter filter) {
        return adviserJdbcRepository.findTriggeredAdvices(filter);
    }

    public void updateTriggeredAdvice(TriggeredAdviceData data) {
        adviserJdbcRepository.updateTriggeredAdvice(data);
    }

    public void deleteTriggeredAdvice(Long id) {
        adviserJdbcRepository.deleteTriggeredAdvice(id);
    }

    public Long getTriggeredAdvicesCount() {
        return adviserJdbcRepository.getTriggeredAdvicesCount();
    }

    public Long getAdvicesCount() {
        return adviserJdbcRepository.getAdvicesCount();
    }

    public void storeResponse(AdviceResponseData data) {
        TriggeredAdviceData triggeredAdvice = getTriggeredAdvice(Long.valueOf(data.getTriggeredAdviceId()));
        TriggeredAdviceData triggeredAdviceToUpdate = new TriggeredAdviceData(data.getAnswer(), LocalDateTime.now(), data.getScore(), triggeredAdvice);

        AdviceData advice = getAdvice(data.getAdviceId());
        if (advice == null) {
            throw new IllegalStateException("advice is null for id: " + data.getAdviceId());
        }
        updateTriggeredAdvice(triggeredAdviceToUpdate);
        createOrUpdateContextVar(new ContextVariableData(triggeredAdvice.getAppId(), triggeredAdvice.getDomain(), advice.getVariableName(), triggeredAdvice.getDataType(), data.getAnswer(), data.getContext().getId()));
    }

    private void createOrUpdateContextVar(ContextVariableData data) {
        List<ContextVariableData> list = contextVariablesService.find(new ContextVariablesFilter(null, null, data.getApplicationId(), data.getName(), data.getContext(), null, data.getContextId()));
        if (list == null || list.size() == 0) {
            contextVariablesService.create(data);
        } else {
            contextVariablesService.update(data);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(TriggeredAdviceLogData data) {
        adviserJdbcRepository.log(data);
    }

    public void changeAdviceStatusWithValidation(AdviceData advice, AdviceStatus status) {
        validateAdviceStatus(status, advice.getStatus());
        changeAdviceStatus(advice, status);
    }

    public void validateApplication(AdviseTriggerData data) {
        applicationsService.validateApplication(data.getAppId(), data.getSecret());
    }

    public boolean validateCategory(AdviceData data) {
        //TODO check if category used/purchased or general
        return true;
    }

    private void validateAdviceStatus(AdviceStatus status, AdviceStatus old) {
        if (AdviceStatus.ACTIVE.equals(old) && AdviceStatus.ONE_TIME_TRIGGERED.equals(status)) {
            ;
        } else {
            throw new IllegalStateException("Status transition from: " + old + " to: " + status + " not allowed");
        }
    }

    private void changeAdviceStatus(AdviceData advice, AdviceStatus status) {
        AdviceData adviceToUpdate = new AdviceData(status, advice);
        adviserJdbcRepository.updateAdvice(adviceToUpdate);
    }

    private void validateAdvice(AdviceData data) {
        ValidationUtil.validateLength(data.getAppId(), 100, "Application ID");
        ValidationUtil.validateLength(data.getDomain(), 100, "Domain");
        ValidationUtil.validateLength(data.getType().code(), 1, "Type");
        ValidationUtil.validateLength(data.getScope(), 100, "Scope");
        ValidationUtil.validateLength(data.getAction(), 500, "Action");
        ValidationUtil.validateLength(data.getDataType(), 100, "Data type");
        ValidationUtil.validateLength(data.getComponent(), 100, "Component");
        ValidationUtil.validateLength(data.getAdviceClass(), 500, "Class");
        ValidationUtil.validateLength(data.getName(), 100, "Name");
        ValidationUtil.validateLength(data.getContent(), 20000, "Content");
    }

}