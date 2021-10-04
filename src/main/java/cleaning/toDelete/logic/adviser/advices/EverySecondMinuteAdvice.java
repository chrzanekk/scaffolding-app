package cleaning.toDelete.logic.adviser.advices;

import cleaning.toDelete.logic.adviser.Advice;
import cleaning.toDelete.logic.adviser.AdviceContext;
import cleaning.toDelete.logic.adviser.TriggeredResult;

import java.time.LocalDateTime;

public class EverySecondMinuteAdvice implements Advice {
    @Override
    public TriggeredResult triggered(AdviceContext ctx) {
        if (LocalDateTime.now().getMinute() % 2 == 0) {
            return TriggeredResult.triggered("" + LocalDateTime.now() + " every sec id: " + ctx.getId());
        }
        return TriggeredResult.noTriggered();
    }
}
