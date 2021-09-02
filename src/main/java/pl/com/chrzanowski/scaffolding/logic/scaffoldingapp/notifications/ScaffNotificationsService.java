package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffNotificationData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffNotificationsFilter;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.Language;

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

    public void validateAndCreate(ScaffNotificationData data) {

        LocalDateTime createDatetime = LocalDateTime.now();
        ScaffUserData user = data.getUser();
        String title = data.getTitle();
        String content = data.getContent();
        String link = data.getLink();
        ScaffNotificationStatus status = data.getStatus();
        ScaffNotificationType type = data.getType();
        ScaffNotificationKind kind = data.getKind();
        Language language = data.getLanguage();

        ScaffNotificationData toCreate = new ScaffNotificationData(createDatetime, null, null,
                user, title, content, link, status, type, kind, language);

        scaffNotificationsValidator.validate(toCreate);
        scaffNotificationsJdbcRepository.create(toCreate);
    }

    public void validateAndUpdate(ScaffNotificationData data) {
        scaffNotificationsValidator.validate(data);
        scaffNotificationsJdbcRepository.update(data);
    }

    @Transactional
    public void validateAndCreate(List<ScaffNotificationData> notifications) {
        for (ScaffNotificationData notification : notifications) {
            validateAndCreate(notification);
        }
    }

    public List<ScaffNotificationData> find(ScaffNotificationsFilter filter) {
        return scaffNotificationsJdbcRepository.find(filter);
    }

    public Long unseenNotificationsCount(ScaffUserData customer) {
        return scaffNotificationsJdbcRepository.unseenNotificationsCount(customer);
    }

    @Transactional
    public void setNotificationsSeen(List<ScaffNotificationData> notifications) {

        List<ScaffNotificationData> unseenNotifications = notifications.stream().filter(n -> n.getSeenDatetime() == null).collect(Collectors.toList());

        for (ScaffNotificationData unseenNotification : unseenNotifications) {
            validateAndUpdate(new ScaffNotificationData(unseenNotification, LocalDateTime.now()));
        }
    }
}
