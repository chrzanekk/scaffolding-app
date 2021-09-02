package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.Language;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationKind;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationStatus;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationType;

import java.time.LocalDateTime;

public class ScaffNotificationData {
    private Long id;
    private LocalDateTime createDatetime;
    private LocalDateTime seenDatetime;
    private LocalDateTime deleteDatetime;
    private ScaffUserData user;
    private String title;
    private String content;
    private String link;
    private ScaffNotificationStatus status;
    private ScaffNotificationType type;
    private ScaffNotificationKind kind;
    private Language language;

    public ScaffNotificationData(Long id, LocalDateTime createDatetime, LocalDateTime seenDatetime,
                                 LocalDateTime deleteDatetime, ScaffUserData user, String title, String content, String link,
                                 ScaffNotificationStatus status, ScaffNotificationType type, ScaffNotificationKind kind, Language language) {
        this.id = id;
        this.createDatetime = createDatetime;
        this.seenDatetime = seenDatetime;
        this.deleteDatetime = deleteDatetime;
        this.user = user;
        this.title = title;
        this.content = content;
        this.link = link;
        this.status = status;
        this.type = type;
        this.kind = kind;
        this.language = language;
    }

    public ScaffNotificationData(LocalDateTime createDatetime, LocalDateTime seenDatetime, LocalDateTime deleteDatetime,
                                 ScaffUserData user, String title, String content, String link, ScaffNotificationStatus status,
                                 ScaffNotificationType type, ScaffNotificationKind kind, Language language) {
        this.createDatetime = createDatetime;
        this.seenDatetime = seenDatetime;
        this.deleteDatetime = deleteDatetime;
        this.user = user;
        this.title = title;
        this.content = content;
        this.link = link;
        this.status = status;
        this.type = type;
        this.kind = kind;
        this.language = language;
    }

    public ScaffNotificationData(ScaffUserData user, String title, String content, String link, ScaffNotificationStatus status, ScaffNotificationType type, ScaffNotificationKind kind, Language language) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.link = link;
        this.status = status;
        this.type = type;
        this.kind = kind;
        this.language = language;
    }

    public ScaffNotificationData(ScaffNotificationData data, LocalDateTime seenDatetime) {
        this.id = data.getId();
        this.createDatetime = data.getCreateDatetime();
        this.seenDatetime = seenDatetime;
        this.deleteDatetime = data.getDeleteDatetime();
        this.user = data.getUser();
        this.title = data.getTitle();
        this.content = data.getContent();
        this.link = data.getLink();
        this.status = data.getStatus();
        this.type = data.getType();
        this.kind = data.getKind();
        this.language = data.getLanguage();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public LocalDateTime getSeenDatetime() {
        return seenDatetime;
    }

    public LocalDateTime getDeleteDatetime() {
        return deleteDatetime;
    }

    public ScaffUserData getUser() {
        return user;
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

    public ScaffNotificationStatus getStatus() {
        return status;
    }

    public ScaffNotificationType getType() {
        return type;
    }

    public ScaffNotificationKind getKind() {
        return kind;
    }

    public Language getLanguage() {
        return language;
    }
}
