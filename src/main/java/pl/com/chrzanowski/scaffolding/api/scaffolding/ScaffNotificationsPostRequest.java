package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffNotificationsPostRequest {
    private String title;
    private String content;
    private String link;
    private String language;

    public ScaffNotificationsPostRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getLanguage() {
        return language;
    }
}
