package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffNotificationData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUsersService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.Language;

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
        List<ScaffUserData> recipients = scaffUsersService.findConfirmEmailNotificationRecipients();
        List<ScaffNotificationData> notifications = prepareNotifications(recipients);
        scaffNotificationsService.validateAndCreate(notifications);
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Successfully created {} confirm email notifications in {} ms", notifications.size(), timeTaken);
    }

    private List<ScaffNotificationData> prepareNotifications(List<ScaffUserData> recipients) {
        List<ScaffNotificationData> notifications = new ArrayList<>();
        for (ScaffUserData recipient : recipients) {
            notifications.add(prepareNotification(recipient));
        }
        return notifications;
    }

    private ScaffNotificationData prepareNotification(ScaffUserData recipient) {
        Language language = Language.from(recipient.getLanguage());
        String title = prepareTitle(language);
        String content = prepareContent(language);
        ScaffNotificationStatus status = ScaffNotificationStatus.SENT;
        ScaffNotificationType type = ScaffNotificationType.PLATFORM;
        ScaffNotificationKind kind = ScaffNotificationKind.EMAIL_CONFIRMATION_REMINDER;
        return new ScaffNotificationData(recipient, title, content, LINK, status, type, kind, language);
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
