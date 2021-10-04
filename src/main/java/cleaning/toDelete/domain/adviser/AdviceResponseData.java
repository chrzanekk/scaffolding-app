package cleaning.toDelete.domain.adviser;

import cleaning.toDelete.api.adviser.ContextRequest;
import cleaning.toDelete.api.adviser.DataSourceRequest;

public class AdviceResponseData {
    private ContextRequest context;
    private Long adviceId;
    private Long triggeredAdviceId;
    private String answer;
    private Long score;

    public AdviceResponseData() {
    }

    public AdviceResponseData(ContextRequest context, DataSourceRequest dataSource, String action, Long adviceId, Long triggeredAdviceId, String answer, Long score) {
        this.context = context;
        this.adviceId = adviceId;
        this.triggeredAdviceId = triggeredAdviceId;
        this.answer = answer;

        this.score = score;
    }

    public ContextRequest getContext() {
        return context;
    }

    public Long getAdviceId() {
        return adviceId;
    }

    public String getAnswer() {
        return answer;
    }

    public Long getScore() {
        return score;
    }

    public Long getTriggeredAdviceId() {
        return triggeredAdviceId;
    }

    @Override
    public String toString() {
        return "RespondPostRequest{" +
                "context=" + context +
                ", adviceId='" + adviceId + '\'' +
                ", triggeredAdviceId='" + triggeredAdviceId + '\'' +
                ", answer='" + answer + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
