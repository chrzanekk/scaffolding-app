package pl.com.chrzanowski.scaffolding.domain;

import pl.com.chrzanowski.scaffolding.logic.notifications.NotificationType;


public class NotificationsFilter {
    private Boolean deleted;
    private UserData user;
    private NotificationType type;
    private Long limit;

    public NotificationsFilter(Boolean deleted, UserData user, NotificationType type, Long limit) {
        this.deleted = deleted;
        this.user = user;
        this.type = type;
        this.limit = limit;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public UserData getUser() {
        return user;
    }

    public NotificationType getType() {
        return type;
    }

    public Long getLimit() {
        return limit;
    }
}
