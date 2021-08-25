package pl.com.chrzanowski.scaffolding.api.adviser;

public class PullGetResponse {
    private String content;

    public PullGetResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
