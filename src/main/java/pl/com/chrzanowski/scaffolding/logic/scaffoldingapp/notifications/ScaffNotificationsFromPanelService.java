package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffCreateNotificationsParameters;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffNotificationData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUsersService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.Language;

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

    public void createNotifications(ScaffCreateNotificationsParameters parameters) {
        validateParameters(parameters);
        List<ScaffUserData> customers = scaffUsersService.find(new ScaffUsersFilter(parameters.getLanguage()));
        List<ScaffNotificationData> notifications = prepareNotifications(customers, parameters);
        scaffNotificationsService.validateAndCreate(notifications);
    }

    private void validateParameters(ScaffCreateNotificationsParameters parameters) {
        scaffNotificationsValidator.validateTitle(parameters.getTitle());
        scaffNotificationsValidator.validateContent(parameters.getContent());
        scaffNotificationsValidator.validateLink(parameters.getLink());
        scaffNotificationsValidator.validateLanguage(parameters.getLanguage());
    }

    private List<ScaffNotificationData> prepareNotifications(List<ScaffUserData> customers,
                                                             ScaffCreateNotificationsParameters parameters) {
        List<ScaffNotificationData> notifications = new ArrayList<>();
        for (ScaffUserData customer : customers) {
            notifications.add(prepareNotification(customer, parameters));
        }
        return notifications;
    }

    private ScaffNotificationData prepareNotification(ScaffUserData customer, ScaffCreateNotificationsParameters parameters) {
        String title = parameters.getTitle();
        String content = parameters.getContent();
        String link = parameters.getLink();
        ScaffNotificationStatus status = ScaffNotificationStatus.SENT;
        ScaffNotificationType type = ScaffNotificationType.PLATFORM;
        ScaffNotificationKind kind = ScaffNotificationKind.FROM_PANEL_ADMIN;
        Language language = parameters.getLanguage();
        return new ScaffNotificationData(customer, title, content, link, status, type, kind, language);
    }
}
