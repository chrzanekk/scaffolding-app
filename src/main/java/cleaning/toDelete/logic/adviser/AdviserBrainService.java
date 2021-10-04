package cleaning.toDelete.logic.adviser;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import cleaning.toDelete.domain.adviser.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdviserBrainService {
    private AdviserService adviserService;
    private ApplicationContext ctx;


    public AdviserBrainService(AdviserService adviserService, ApplicationContext ctx) {
        this.adviserService = adviserService;
        this.ctx = ctx;
    }


    public AdviceData pullAdvise(AdvisePullData data) {
//        // pull the oldest one and mark as pulled/remove
//        List<AdviceData> list = triggeredAdvices.get(data.getId());
//        if (list != null || !list.isEmpty()) {
//            AdviceData res = list.get(0);
//            list.remove(0);
//            return res;
//        }
        return new AdviceData("Error");
    }

    public TriggeredAdviceData triggerAdvises(AdviseTriggerData data) {
        adviserService.validateApplication(data);

        List<AdviceData> advicesInContext = adviserService.findAdvices(
                new AdvicesFilter(data.getAppId(), data.getDomain(), null, null, LocalDateTime.now(), LocalDateTime.now(), AdviceStatus.ACTIVE)); //TODO scope, action, only one advise
        if (advicesInContext != null) {
            for (AdviceData adviceData : advicesInContext) {
                boolean process = adviserService.validateCategory(adviceData);
                if (!process) {
                    continue;
                }
                process = startProcessing(adviceData);
                if (process) {
                    Long triggeredAdviceId = null;
                    try {
                        Advice advice = prepareAdvice(adviceData);
                        TriggeredResult res = advice.triggered(new AdviceContext(data.getId(), prepareDataSource(data), adviceData));
                        if (res.wasTriggered()) {
                            TriggeredAdviceData triggeredAdviceData = storeTriggeredAdvice(new AdviceData(res.getContent(), adviceData), data.getId());
                            triggeredAdviceId = triggeredAdviceData.getId();

                            finishProcessing(adviceData);
                            return triggeredAdviceData;
                        }
                    } catch (Exception ex) {
                        adviserService.log(new TriggeredAdviceLogData(adviceData.getId(), triggeredAdviceId, adviceData.getAppId(), adviceData.getDomain(), ex.getMessage()));
                        return null;
                    }
                }
            }
        }
        return null;
    }

    private void finishProcessing(AdviceData advice) {
        if (AdvicePeriod.ONE_TIME.equals(advice.getPeriod())) {


        }
    }

    private boolean startProcessing(AdviceData advice) {
        if (AdvicePeriod.ONE_TIME.equals(advice.getPeriod())) {
            // TODO check if was triggered ealier !!!!!!!!!!!!
        }
        return true;
    }

    private Advice prepareAdvice(AdviceData advice) {
        return (Advice) ctx.getBean(advice.getAdviceClass());
    }

    private AdviseDataSource prepareDataSource(AdviseTriggerData data) {


        AdviseDataSource dataSource = new AdviseDataSource();
        if ("user".equals(data.getDomain())) {

            dataSource.add(data.getDomain(), "login", "pkbiker" /* to get from db*/);
            // ...
        }
        return dataSource;
    }

    private TriggeredAdviceData storeTriggeredAdvice(AdviceData advice, String domainId) {
        TriggeredAdviceData data = new TriggeredAdviceData(
                advice.getId(), // adviceId,
                advice.getAppId(), ////  appId,
                advice.getDomain(), ////  domain,
                domainId, ////  domainId,
                advice.getContent(), ////  content,
                advice.getType().code(), ////  type,
                null, ////  lang,
                advice.getScope(), ////  scope,
                advice.getAction(), ////  action,
                advice.getDataType(), ////  dataType,
                advice.getComponent(), ////  component,
                0L, ////  score,
                "N", ////  status
                LocalDateTime.now(),
                advice.getName(),
                advice.getAnswerOptions()
        );
        Long id = adviserService.createTriggeredAdvice(data);
        return new TriggeredAdviceData(id, data);
    }

}
