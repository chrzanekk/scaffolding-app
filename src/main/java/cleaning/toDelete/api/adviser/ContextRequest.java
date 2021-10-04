package cleaning.toDelete.api.adviser;

public class ContextRequest {
    private String domain;
    private String id;
    private String appId;
    private String secret;
    private String lang;

    public ContextRequest() {
    }

    public ContextRequest(String domain, String id, String appId, String secret, String lang) {
        this.domain = domain;
        this.id = id;
        this.appId = appId;
        this.secret = secret;
        this.lang = lang;
    }

    public String getDomain() {
        return domain;
    }

    public String getId() {
        return id;
    }

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "ContextRequest{" +
                "domain='" + domain + '\'' +
                ", id='" + id + '\'' +
                ", appId='" + appId + '\'' +
                ", secret='" + secret + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
