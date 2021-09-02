package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationType;


public class ScaffNotificationsFilter {
    private Boolean deleted;
    private ScaffUserData user;
    private ScaffNotificationType type;
    private Long limit;

    public ScaffNotificationsFilter(Boolean deleted, ScaffUserData user, ScaffNotificationType type, Long limit) {
        this.deleted = deleted;
        this.user = user;
        this.type = type;
        this.limit = limit;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public ScaffUserData getUser() {
        return user;
    }

    public ScaffNotificationType getType() {
        return type;
    }

    public Long getLimit() {
        return limit;
    }
}
