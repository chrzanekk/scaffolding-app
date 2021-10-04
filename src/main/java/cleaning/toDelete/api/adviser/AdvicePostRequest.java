package cleaning.toDelete.api.adviser;

public class AdvicePostRequest {
    private String appId;
    private String domain;
    private String content;
    private String type;
    private String scope;
    private String action;
    private String dataType;
    private String component;
    private String adviceClass;
    private String name;

    public AdvicePostRequest() {
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

    public String getType() {
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

    @Override
    public String toString() {
        return "AdvicePostRequest{" +
                "appId='" + appId + '\'' +
                ", domain='" + domain + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", scope='" + scope + '\'' +
                ", action='" + action + '\'' +
                ", dataType='" + dataType + '\'' +
                ", component='" + component + '\'' +
                ", adviceClass='" + adviceClass + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
