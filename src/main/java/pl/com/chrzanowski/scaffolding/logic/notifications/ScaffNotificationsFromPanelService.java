package pl.com.chrzanowski.scaffolding.logic.notifications;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.CreateNotificationsParameters;
import pl.com.chrzanowski.scaffolding.domain.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;
import pl.com.chrzanowski.scaffolding.logic.ScaffUsersService;
import pl.com.chrzanowski.scaffolding.logic.Language;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScaffNotificationsFromPanelService {
    private final ScaffUsersService scaffUsersService;
    private final ScaffNotificationsService scaffNotificationsService;
    private final ScaffNotificationsValidator scaffNotificationsValidator;

    public ScaffNotificationsFromPanelService(ScaffUsersService scaffUsersService, ScaffNotificationsService scaffNotificationsService,
                                              ScaffNotificationsValidator scaffNotificationsValidator) {
        this.scaffUsersService = scaffUsersService;
        this.scaffNotificationsService = scaffNotificationsService;
        this.scaffNotificationsValidator = scaffNotificationsValidator;
    }

    public void createNotifications(CreateNotificationsParameters parameters) {
        validateParameters(parameters);
        List<UserData> customers = scaffUsersService.find(new UsersFilter(parameters.getLanguage()));
        List<NotificationData> notifications = prepareNotifications(customers, parameters);
        scaffNotificationsService.validateAndCreate(notifications);
    }

    private void validateParameters(CreateNotificationsParameters parameters) {
        scaffNotificationsValidator.validateTitle(parameters.getTitle());
        scaffNotificationsValidator.validateContent(parameters.getContent());
        scaffNotificationsValidator.validateLink(parameters.getLink());
        scaffNotificationsValidator.validateLanguage(parameters.getLanguage());
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
        ScaffNotificationStatus status = ScaffNotificationStatus.SENT;
        ScaffNotificationType type = ScaffNotificationType.PLATFORM;
        ScaffNotificationKind kind = ScaffNotificationKind.FROM_PANEL_ADMIN;
        Language language = parameters.getLanguage();
        return new NotificationData(customer, title, content, link, status, type, kind, language);
    }
}
