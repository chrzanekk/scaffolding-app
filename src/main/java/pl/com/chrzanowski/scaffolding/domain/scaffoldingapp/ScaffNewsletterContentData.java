package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

public class ScaffNewsletterContentData {

    private String html;
    private String title;

    public ScaffNewsletterContentData() {
    }

    public ScaffNewsletterContentData(String html, String title) {
        this.html = html;
        this.title = title;
    }

    public String getHtml() {
        return html;
    }

    public String getTitle() {
        return title;
    }
}
