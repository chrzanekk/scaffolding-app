package cleaning.toDelete.api.adviser;

import cleaning.toDelete.domain.adviser.AdviceResponseData;

public class RespondPostRequest {
    private ContextRequest context;
    private String adviceId;
    private String triggeredAdviceId;
    private String answer;
    private String score;

    public RespondPostRequest() {
    }

    public RespondPostRequest(ContextRequest context, DataSourceRequest dataSource, String action, String triggeredAdviceId, String score) {
        this.context = context;
        this.triggeredAdviceId = triggeredAdviceId;

        this.score = score;
    }

    public ContextRequest getContext() {
        return context;
    }

    public String getAdviceId() {
        return adviceId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getScore() {
        return score;
    }

    public String getTriggeredAdviceId() {
        return triggeredAdviceId;
    }

    AdviceResponseData toAdviceResponseData() {
        return new AdviceResponseData(this.getContext(), null, null, Long.valueOf(this.adviceId), Long.valueOf(this.triggeredAdviceId), this.answer, Long.valueOf(this.score));
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
