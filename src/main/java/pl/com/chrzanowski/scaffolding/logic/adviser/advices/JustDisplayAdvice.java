package pl.com.chrzanowski.scaffolding.logic.adviser.advices;

import pl.com.chrzanowski.scaffolding.logic.adviser.Advice;
import pl.com.chrzanowski.scaffolding.logic.adviser.AdviceContext;
import pl.com.chrzanowski.scaffolding.logic.adviser.AdviceOperationsService;
import pl.com.chrzanowski.scaffolding.logic.adviser.TriggeredResult;

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
