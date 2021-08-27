package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserData;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.UsersService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.Language;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmEmailNotificationsService {

    private static final Logger log = LoggerFactory.getLogger(ConfirmEmailNotificationsService.class);

    private static final String LINK = "/account-settings";

    private final NotificationsService notificationsService;
    private final UsersService usersService;

    public ConfirmEmailNotificationsService(NotificationsService notificationsService, UsersService usersService) {
        this.notificationsService = notificationsService;
        this.usersService = usersService;
    }

    @Scheduled(cron = "0 0 4 * * *")
    private void createNotifications() {
        long startTime = System.currentTimeMillis();
        log.info("Start confirm email notifications creating");
        List<UserData> recipients = usersService.findConfirmEmailNotificationRecipients();
        List<NotificationData> notifications = prepareNotifications(recipients);
        notificationsService.validateAndCreate(notifications);
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
        NotificationStatus status = NotificationStatus.SENT;
        NotificationType type = NotificationType.PLATFORM;
        NotificationKind kind = NotificationKind.EMAIL_CONFIRMATION_REMINDER;
        return new NotificationData(recipient, title, content, LINK, status, type, kind, language);
    }

    private String prepareTitle(Language language) {
        if (language == Language.POLISH) {
            return "Potwierdź email";
        } else {
            return "Confirm email";
        }
    }

    private String prepareContent(Language language) {
        if (language == Language.POLISH) {
            return "Kliknij w powiadomienie aby przejść do ustawień";
        } else {
            return "Click on the notification to go to settings";
        }
    }
}
