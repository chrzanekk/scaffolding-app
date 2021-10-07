package pl.com.chrzanowski.scaffolding.logic.notifications;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.NotificationsFilter;
import pl.com.chrzanowski.scaffolding.logic.Language;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScaffNotificationsService {

    private ScaffNotificationsJdbcRepository scaffNotificationsJdbcRepository;
    private ScaffNotificationsValidator scaffNotificationsValidator;

    public ScaffNotificationsService(ScaffNotificationsJdbcRepository scaffNotificationsJdbcRepository, ScaffNotificationsValidator scaffNotificationsValidator) {
        this.scaffNotificationsJdbcRepository = scaffNotificationsJdbcRepository;
        this.scaffNotificationsValidator = scaffNotificationsValidator;
    }

    public void validateAndCreate(NotificationData data) {

        LocalDateTime createDatetime = LocalDateTime.now();
        UserData user = data.getUser();
        String title = data.getTitle();
        String content = data.getContent();
        String link = data.getLink();
        ScaffNotificationStatus status = data.getStatus();
        ScaffNotificationType type = data.getType();
        ScaffNotificationKind kind = data.getKind();
        Language language = data.getLanguage();

        NotificationData toCreate = new NotificationData(createDatetime, null, null,
                user, title, content, link, status, type, kind, language);

        scaffNotificationsValidator.validate(toCreate);
        scaffNotificationsJdbcRepository.create(toCreate);
    }

    public void validateAndUpdate(NotificationData data) {
        scaffNotificationsValidator.validate(data);
        scaffNotificationsJdbcRepository.update(data);
    }

    @Transactional
    public void validateAndCreate(List<NotificationData> notifications) {
        for (NotificationData notification : notifications) {
            validateAndCreate(notification);
        }
    }

    public List<NotificationData> find(NotificationsFilter filter) {
        return scaffNotificationsJdbcRepository.find(filter);
    }

    public Long unseenNotificationsCount(UserData customer) {
        return scaffNotificationsJdbcRepository.unseenNotificationsCount(customer);
    }

    @Transactional
    public void setNotificationsSeen(List<NotificationData> notifications) {

        List<NotificationData> unseenNotifications = notifications.stream().filter(n -> n.getSeenDatetime() == null).collect(Collectors.toList());

        for (NotificationData unseenNotification : unseenNotifications) {
            validateAndUpdate(new NotificationData(unseenNotification, LocalDateTime.now()));
        }
    }
}
