package cleaning.toDelete.logic.adviser.advices;

import cleaning.toDelete.logic.adviser.Advice;
import cleaning.toDelete.logic.adviser.AdviceContext;
import cleaning.toDelete.logic.adviser.TriggeredResult;

import java.time.LocalDateTime;

public class EveryFourthSecondAdvice implements Advice {
    @Override
    public TriggeredResult triggered(AdviceContext ctx) {
        if (LocalDateTime.now().getSecond() % 4 == 0) {
            return TriggeredResult.triggered(" (4) " + LocalDateTime.now() + " id: " + ctx.getId() + " " + ctx.getDataSource());
        }
        return TriggeredResult.noTriggered();
    }
}
