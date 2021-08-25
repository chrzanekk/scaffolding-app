package pl.com.chrzanowski.scaffolding.logic.adviser;

public interface Advice {
    TriggeredResult triggered(AdviceContext ctx);

}
