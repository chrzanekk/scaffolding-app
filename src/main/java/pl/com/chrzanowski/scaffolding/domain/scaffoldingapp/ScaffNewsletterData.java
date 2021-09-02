package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import java.util.HashMap;
import java.util.Map;

public class ScaffNewsletterData {
    private Map<String, ScaffNewsletterContentData> content = new HashMap<>();

    public ScaffNewsletterData() {
    }

    public ScaffNewsletterData(Map<String, ScaffNewsletterContentData> content) {
        this.content = content;
    }

    public Map<String, ScaffNewsletterContentData> getContent() {
        return content;
    }
}
