package pl.com.chrzanowski.scaffolding.logic.adviser.advices;

import pl.com.chrzanowski.scaffolding.logic.adviser.Advice;
import pl.com.chrzanowski.scaffolding.logic.adviser.AdviceContext;
import pl.com.chrzanowski.scaffolding.logic.adviser.TriggeredResult;

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
