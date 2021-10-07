package pl.com.chrzanowski.scaffolding.logic.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.logic.ScaffUsersService;
import pl.com.chrzanowski.scaffolding.logic.Language;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScaffConfirmEmailNotificationsService {

    private static final Logger log = LoggerFactory.getLogger(ScaffConfirmEmailNotificationsService.class);

    private static final String LINK = "/account-settings";

    private final ScaffNotificationsService scaffNotificationsService;
    private final ScaffUsersService scaffUsersService;

    public ScaffConfirmEmailNotificationsService(ScaffNotificationsService scaffNotificationsService, ScaffUsersService scaffUsersService) {
        this.scaffNotificationsService = scaffNotificationsService;
        this.scaffUsersService = scaffUsersService;
    }

    @Scheduled(cron = "0 0 4 * * *")
    private void createNotifications() {
        long startTime = System.currentTimeMillis();
        log.info("Start confirm email notifications creating");
        List<UserData> recipients = scaffUsersService.findConfirmEmailNotificationRecipients();
        List<NotificationData> notifications = prepareNotifications(recipients);
        scaffNotificationsService.validateAndCreate(notifications);
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Successfully created {} confirm email notifications in {} ms", notifications.size(), timeTaken);
    }

    private List<NotificationData> prepareNotifications(List<UserData> recipients) {
        List<NotificationData> notifications = new ArrayList<>();
        for (UserData recipient : recipients) {
            notifications.add(prepareNotification(recipient));
        }
        return notifications;
    }

    private NotificationData prepareNotification(UserData recipient) {
        Language language = Language.from(recipient.getLanguage());
        String title = prepareTitle(language);
        String content = prepareContent(language);
        ScaffNotificationStatus status = ScaffNotificationStatus.SENT;
        ScaffNotificationType type = ScaffNotificationType.PLATFORM;
        ScaffNotificationKind kind = ScaffNotificationKind.EMAIL_CONFIRMATION_REMINDER;
        return new NotificationData(recipient, title, content, LINK, status, type, kind, language);
    }

    private String prepareTitle(Language language) {
        if (language == Language.PL) {
            return "Potwierdź email";
        } else {
            return "Confirm email";
        }
    }

    private String prepareContent(Language language) {
        if (language == Language.PL) {
            return "Kliknij w powiadomienie aby przejść do ustawień";
        } else {
            return "Click on the notification to go to settings";
        }
    }
}
