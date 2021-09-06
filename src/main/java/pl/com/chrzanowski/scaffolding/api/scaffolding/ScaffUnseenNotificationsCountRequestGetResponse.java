package pl.com.chrzanowski.scaffolding.api.scaffolding;

public class ScaffUnseenNotificationsCountRequestGetResponse {
    private Long count;

    public ScaffUnseenNotificationsCountRequestGetResponse(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }
}
