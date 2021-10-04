package cleaning.toDelete.logic.adviser.advices;

import cleaning.toDelete.logic.adviser.Advice;
import cleaning.toDelete.logic.adviser.AdviceContext;
import cleaning.toDelete.logic.adviser.TriggeredResult;

import java.time.LocalDateTime;

public class GetUserLoginAdviceInHour implements Advice {
    @Override
    public TriggeredResult triggered(AdviceContext ctx) {
        if (LocalDateTime.now().getHour() == 21) {
            return TriggeredResult.triggered(ctx.getDataSource().get("user", "login"));
        }
        return TriggeredResult.noTriggered();
    }
}
