package pl.com.chrzanowski.scaffolding.api.adviser;

import pl.com.chrzanowski.scaffolding.domain.adviser.TriggeredAdviceData;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TriggeredAdviceGetResponse {

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
    private String triggerDateTime;
    private String name;
    private List<AdviceAnswerOptionGetResponse> answerOptions;

    public TriggeredAdviceGetResponse(Long id, String name, String appId, String domain, String triggerDateTime) {
        this.id = id;
        this.name = name;
        this.appId = appId;
        this.domain = domain;
        this.triggerDateTime = triggerDateTime;
    }

    public TriggeredAdviceGetResponse(TriggeredAdviceData data) {
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
        this.triggerDateTime = data.getTriggerDateTime().toString();
        this.name = data.getName();
        this.answerOptions = prepareAnswerOptions(data);
    }

    private List<AdviceAnswerOptionGetResponse> prepareAnswerOptions(TriggeredAdviceData data) {
        if (data.getAnswerOptions() == null) {
            return Collections.emptyList();
        }
        return data.getAnswerOptions().stream().map(o -> new AdviceAnswerOptionGetResponse(o.getName(), o.getValue())).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAppId() {
        return appId;
    }

    public String getDomain() {
        return domain;
    }

    public String getTriggerDateTime() {
        return triggerDateTime;
    }

    public Long getAdviceId() {
        return adviceId;
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

    public List<AdviceAnswerOptionGetResponse> getAnswerOptions() {
        return answerOptions;
    }
}
