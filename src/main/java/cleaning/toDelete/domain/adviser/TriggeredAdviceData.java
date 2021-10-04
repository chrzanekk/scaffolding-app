package cleaning.toDelete.domain.adviser;

import java.time.LocalDateTime;
import java.util.List;

public class TriggeredAdviceData {
    private Long id;
    private Long adviceId;
    private String appId;
    private String domain;
    private String domainId;
    private String content;
    private String type;
    private String lang;
    private String scope;
    private String action;
    private String dataType;
    private String component;
    private Long score;
    private String status;
    private LocalDateTime triggerDateTime;
    private String name;
    private String responseValue;
    private LocalDateTime responseDateTime;
    private LocalDateTime removeDateTime;
    private List<AdviceAnswerOptionData> answerOptions;

    public TriggeredAdviceData(Long adviceId, String appId, String domain, String domainId, String content, String type, String lang, String scope, String action, String dataType, String component, Long score, String status, LocalDateTime triggerDateTime, String name, List<AdviceAnswerOptionData> answerOptions) {
        this.adviceId = adviceId;
        this.appId = appId;
        this.domain = domain;
        this.domainId = domainId;
        this.content = content;
        this.type = type;
        this.lang = lang;
        this.scope = scope;
        this.action = action;
        this.dataType = dataType;
        this.component = component;
        this.score = score;
        this.status = status;
        this.triggerDateTime = triggerDateTime;
        this.name = name;
        this.answerOptions = answerOptions;
    }

    public TriggeredAdviceData(Long id, Long adviceId, String appId, String domain, String domainId, String content, String type, String lang, String scope, String action, String dataType, String component, Long score, String status, LocalDateTime triggerDateTime, String name, LocalDateTime responseDateTime,
                               String responseValue) {
        this.adviceId = adviceId;
        this.appId = appId;
        this.domain = domain;
        this.domainId = domainId;
        this.content = content;
        this.type = type;
        this.lang = lang;
        this.scope = scope;
        this.action = action;
        this.dataType = dataType;
        this.component = component;
        this.score = score;
        this.status = status;
        this.triggerDateTime = triggerDateTime;
        this.name = name;
        this.id = id;
        this.responseValue = responseValue;
        this.responseDateTime = responseDateTime;
    }

    private void setFields(TriggeredAdviceData data) {
        this.id = data.getId();
        this.adviceId = data.getAdviceId();
        this.appId = data.getAppId();
        this.domain = data.getDomain();
        this.domainId = data.getDomainId();
        this.content = data.getContent();
        this.type = data.getType();
        this.lang = data.getLang();
        this.scope = data.getScope();
        this.action = data.getAction();
        this.dataType = data.getDataType();
        this.component = data.getComponent();
        this.score = data.getScore();
        this.status = data.getStatus();
        this.triggerDateTime = data.getTriggerDateTime();
        this.name = data.getName();
        this.responseValue = data.getResponseValue();
        this.responseDateTime = data.getResponseDateTime();
    }

    public TriggeredAdviceData(Long id, TriggeredAdviceData data) {
        setFields(data);

        this.id = id;
        this.answerOptions = data.getAnswerOptions();
    }

    public TriggeredAdviceData(LocalDateTime removeDateTime, TriggeredAdviceData data) {
        setFields(data);
        this.removeDateTime = removeDateTime;
    }

    public TriggeredAdviceData(String responseValue,
                               LocalDateTime responseDateTime, Long score, TriggeredAdviceData data) {
        setFields(data);

        this.id = data.getId();
        this.responseValue = responseValue;
        this.responseDateTime = responseDateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getAdviceId() {
        return adviceId;
    }

    public String getAppId() {
        return appId;
    }

    public String getDomain() {
        return domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getLang() {
        return lang;
    }

    public String getScope() {
        return scope;
    }

    public String getAction() {
        return action;
    }

    public String getDataType() {
        return dataType;
    }

    public String getComponent() {
        return component;
    }

    public Long getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTriggerDateTime() {
        return triggerDateTime;
    }

    public String getName() {
        return name;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public LocalDateTime getResponseDateTime() {
        return responseDateTime;
    }

    public List<AdviceAnswerOptionData> getAnswerOptions() {
        return answerOptions;
    }

    public LocalDateTime getRemoveDateTime() {
        return removeDateTime;
    }

    @Override
    public String toString() {
        return "TriggeredAdviceData{" +
                "id=" + id +
                ", adviceId=" + adviceId +
                ", appId='" + appId + '\'' +
                ", domain='" + domain + '\'' +
                ", domainId='" + domainId + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", lang='" + lang + '\'' +
                ", scope='" + scope + '\'' +
                ", action='" + action + '\'' +
                ", dataType='" + dataType + '\'' +
                ", component='" + component + '\'' +
                ", score=" + score +
                ", status='" + status + '\'' +
                ", triggerDateTime=" + triggerDateTime +
                ", name='" + name + '\'' +
                ", responseValue='" + responseValue + '\'' +
                ", responseDateTime=" + responseDateTime +
                ", removeDate=" + removeDateTime +
                ", answerOptions=" + answerOptions +
                '}';
    }
}
