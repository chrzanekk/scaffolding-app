package pl.com.chrzanowski.scaffolding.logic.notifications;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.CreateNotificationsParameters;
import pl.com.chrzanowski.scaffolding.domain.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;
import pl.com.chrzanowski.scaffolding.logic.UserService;
import pl.com.chrzanowski.scaffolding.logic.Language;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationsFromPanelService {
    private final UserService userService;
    private final NotificationsService notificationsService;
    private final NotificationsValidator notificationsValidator;

    public NotificationsFromPanelService(UserService userService, NotificationsService notificationsService,
                                         NotificationsValidator notificationsValidator) {
        this.userService = userService;
        this.notificationsService = notificationsService;
        this.notificationsValidator = notificationsValidator;
    }

    public void createNotifications(CreateNotificationsParameters parameters) {
        validateParameters(parameters);
        List<UserData> customers = userService.find(new UsersFilter(parameters.getLanguage()));
        List<NotificationData> notifications = prepareNotifications(customers, parameters);
        notificationsService.validateAndCreate(notifications);
    }

    private void validateParameters(CreateNotificationsParameters parameters) {
        notificationsValidator.validateTitle(parameters.getTitle());
        notificationsValidator.validateContent(parameters.getContent());
        notificationsValidator.validateLink(parameters.getLink());
        notificationsValidator.validateLanguage(parameters.getLanguage());
    }

    private List<NotificationData> prepareNotifications(List<UserData> customers,
                                                        CreateNotificationsParameters parameters) {
        List<NotificationData> notifications = new ArrayList<>();
        for (UserData customer : customers) {
            notifications.add(prepareNotification(customer, parameters));
        }
        return notifications;
    }

    private NotificationData prepareNotification(UserData customer, CreateNotificationsParameters parameters) {
        String title = parameters.getTitle();
        String content = parameters.getContent();
        String link = parameters.getLink();
        NotificationStatus status = NotificationStatus.SENT;
        NotificationType type = NotificationType.PLATFORM;
        NotificationKind kind = NotificationKind.FROM_PANEL_ADMIN;
        Language language = parameters.getLanguage();
        return new NotificationData(customer, title, content, link, status, type, kind, language);
    }
}
