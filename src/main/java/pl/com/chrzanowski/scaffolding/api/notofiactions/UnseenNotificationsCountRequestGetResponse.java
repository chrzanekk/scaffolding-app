package pl.com.chrzanowski.scaffolding.api.notofiactions;

public class UnseenNotificationsCountRequestGetResponse {
    private Long count;

    public UnseenNotificationsCountRequestGetResponse(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }
}
