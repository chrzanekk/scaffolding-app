package pl.com.chrzanowski.scaffolding.domain.adviser;

import pl.com.chrzanowski.scaffolding.api.adviser.AdvicePostRequest;
import pl.com.chrzanowski.scaffolding.api.adviser.AdvicePutRequest;

import java.util.List;

public class AdviceData {
    private Long id;
    private String appId;
    private String domain;
    private String content;
    private AdviseType type;
    private String scope;
    private String action;
    private String dataType;
    private String component;
    private String adviceClass;
    private String name;
    private AdvicePeriod period;
    private AdviceStatus status;
    private String variableName;
    private List<AdviceAnswerOptionData> answerOptions;


    public AdviceData(String appId, String domain, String content, AdviseType type, String scope, String action, String dataType, String component, String adviceClass) {
        this.appId = appId;
        this.domain = domain;
        this.content = content;
        this.type = type;
        this.scope = scope;
        this.action = action;
        this.dataType = dataType;
        this.component = component;
        this.adviceClass = adviceClass;
    }

    public AdviceData(AdvicePostRequest request) {
        this.appId = request.getAppId();
        this.domain = request.getDomain();
        this.content = request.getContent();
        this.type = AdviseType.fromCode(request.getType());
        this.scope = request.getScope();
        this.action = request.getAction();
        this.dataType = request.getDataType();
        this.component = request.getComponent();
        this.adviceClass = request.getAdviceClass();
        this.name = request.getName();
    }


    public AdviceData(Long id, AdvicePutRequest request) {
        this.id = id;
        this.appId = request.getAppId();
        this.domain = request.getDomain();
        this.content = request.getContent();
        this.type = AdviseType.fromCode(request.getType());
        this.scope = request.getScope();
        this.action = request.getAction();
        this.dataType = request.getDataType();
        this.component = request.getComponent();
        this.adviceClass = request.getAdviceClass();
        this.name = request.getName();
    }

    public AdviceData(Long id, String appId, String domain, String content, AdviseType type, String scope, String action, String dataType, String component, String adviceClass, String name, AdviceStatus status, AdvicePeriod period, String variableName) {
        this.id = id;
        this.appId = appId;
        this.domain = domain;
        this.content = content;
        this.type = type;
        this.scope = scope;
        this.action = action;
        this.dataType = dataType;
        this.component = component;
        this.adviceClass = adviceClass;
        this.name = name;
        this.status = status;
        this.period = period;
        this.variableName = variableName;
    }

    public AdviceData(Long id, String appId, String domain, String content, AdviseType type, String scope, String action, String dataType, String component, String adviceClass, String name, AdviceStatus status, AdvicePeriod period, String variableName, List<AdviceAnswerOptionData> answerOptions) {
        this.id = id;
        this.appId = appId;
        this.domain = domain;
        this.content = content;
        this.type = type;
        this.scope = scope;
        this.action = action;
        this.dataType = dataType;
        this.component = component;
        this.adviceClass = adviceClass;
        this.name = name;
        this.status = status;
        this.period = period;
        this.variableName = variableName;
        this.answerOptions = answerOptions;
    }

    public AdviceData(String content, AdviceData data) {
        setFields(data);
        this.content = content;
    }

    public AdviceData(AdviceStatus status, AdviceData data) {
        setFields(data);
        this.status = status;
    }

    private void setFields(AdviceData data) {
        this.id = data.getId();
        this.appId = data.getAppId();
        this.domain = data.getDomain();
        this.type = data.getType();
        this.scope = data.getScope();
        this.action = data.getAction();
        this.dataType = data.getDataType();
        this.component = data.getComponent();
        this.adviceClass = data.getAdviceClass();
        this.name = data.getName();
        this.content = data.getContent();
        this.status = data.getStatus();
        this.answerOptions = data.getAnswerOptions();
    }

    public AdviceData(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getAppId() {
        return appId;
    }

    public String getDomain() {
        return domain;
    }

    public String getContent() {
        return content;
    }

    public AdviseType getType() {
        return type;
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

    public String getAdviceClass() {
        return adviceClass;
    }

    public String getName() {
        return name;
    }

    public AdvicePeriod getPeriod() {
        return period;
    }

    public AdviceStatus getStatus() {
        return status;
    }

    public String getVariableName() {
        return variableName;
    }

    public List<AdviceAnswerOptionData> getAnswerOptions() {
        return answerOptions;
    }

    @Override
    public String toString() {
        return "AdviceData{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", domain='" + domain + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", scope='" + scope + '\'' +
                ", action='" + action + '\'' +
                ", dataType='" + dataType + '\'' +
                ", component='" + component + '\'' +
                ", adviceClass='" + adviceClass + '\'' +
                ", name='" + name + '\'' +
                ", period=" + period +
                ", status=" + status +
                ", variableName='" + variableName + '\'' +
                ", answerOptions=" + answerOptions +
                '}';
    }
}
