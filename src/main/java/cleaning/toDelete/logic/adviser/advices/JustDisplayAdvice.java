package cleaning.toDelete.logic.adviser.advices;

import cleaning.toDelete.logic.adviser.Advice;
import cleaning.toDelete.logic.adviser.AdviceContext;
import cleaning.toDelete.logic.adviser.AdviceOperationsService;
import cleaning.toDelete.logic.adviser.TriggeredResult;

public class JustDisplayAdvice implements Advice {
    private AdviceOperationsService adviceOperationsService;

    public JustDisplayAdvice(AdviceOperationsService adviceOperationsService) {
        this.adviceOperationsService = adviceOperationsService;
    }

    @Override
    public TriggeredResult triggered(AdviceContext ctx) {
        return TriggeredResult.triggered(ctx.getAdvice().getContent());
    }
}
