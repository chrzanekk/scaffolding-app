package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class SentEmailData {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String language;
    private String event;
    private LocalDateTime createDatetime;

    public SentEmailData(Long userId, String title, String content, String language, String event) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.language = language;
        this.event = event;
    }

    public SentEmailData(SentEmailData data, LocalDateTime createDatetime) {
        this.id = data.getId();
        this.userId = data.getUserId();
        this.title = data.getTitle();
        this.content = data.getContent();
        this.language = data.getLanguage();
        this.event = data.getEvent();
        this.createDatetime = createDatetime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLanguage() {
        return language;
    }

    public String getEvent() {
        return event;
    }

    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }
}
