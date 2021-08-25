package pl.com.chrzanowski.scaffolding.logic.adviser.advices;

import pl.com.chrzanowski.scaffolding.logic.adviser.Advice;
import pl.com.chrzanowski.scaffolding.logic.adviser.AdviceContext;
import pl.com.chrzanowski.scaffolding.logic.adviser.TriggeredResult;

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
