package pl.com.chrzanowski.scaffolding.api.scaffolding;

import java.util.List;

public class ScaffNotificationsRequestGetResponse {
    private final List<ScaffNotificationGetResponse> notifications;

    public ScaffNotificationsRequestGetResponse(List<ScaffNotificationGetResponse> notifications) {
        this.notifications = notifications;
    }

    public List<ScaffNotificationGetResponse> getNotifications() {
        return notifications;
    }
}
