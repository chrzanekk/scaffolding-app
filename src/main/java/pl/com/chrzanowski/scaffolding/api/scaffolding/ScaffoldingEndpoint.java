package pl.com.chrzanowski.scaffolding.api.scaffolding;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.*;
import pl.com.chrzanowski.scaffolding.logic.courseplatform.WebUtil;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffEmailConfirmService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffPasswordResetTokensService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffTraceService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUsersService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationType;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/scaffolding")
public class ScaffoldingEndpoint {

    private ScaffUsersService scaffUsersService;
    private ScaffPasswordResetTokensService scaffPasswordResetTokensService;
    private ScaffTraceService scaffTraceService;
    private ScaffEmailConfirmService scaffEmailConfirmService;
    private ScaffNotificationsService scaffNotificationsService;

    public ScaffoldingEndpoint(ScaffUsersService scaffUsersService, ScaffPasswordResetTokensService scaffPasswordResetTokensService, ScaffTraceService scaffTraceService, ScaffEmailConfirmService scaffEmailConfirmService, ScaffNotificationsService scaffNotificationsService) {
        this.scaffUsersService = scaffUsersService;
        this.scaffPasswordResetTokensService = scaffPasswordResetTokensService;
        this.scaffTraceService = scaffTraceService;
        this.scaffEmailConfirmService = scaffEmailConfirmService;
        this.scaffNotificationsService = scaffNotificationsService;
    }

    @PostMapping("/register-user")
    public void register(@RequestBody ScaffUserPostRequest request, HttpServletRequest httpServletRequest) {
        ScaffUserData userCreateRequest = new ScaffUserData(
                request.getLogin(),
                request.getPasswordHash(),
                request.getLanguage(),
                request.getRegulationAccepted(),
                request.getNewsletterAccepted(),
                request.getIsEnabled(),
                request.getIsEmailConfirmed(),
                httpServletRequest);
        scaffUsersService.registerUser(userCreateRequest);
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody ScaffChangePasswordRequest request) {
        scaffUsersService.changeLoggedUsersPassword(request.getActualPasswordHash(), request.getNewPasswordHash());
    }

    @PutMapping("/logged-user")
    public void updateLoggedUser(@RequestBody ScaffLoggedUserPutRequest request) {
        ScaffUserData loggedUser = scaffUsersService.getLoggedUser();

        if (loggedUser == null) {
            throw new IllegalArgumentException("Your are logged out. Please log in and retry");
        }
        scaffUsersService.updateLoggedUser(request.getLanguage(), request.getNewsletterAccepted());
    }

    @PostMapping("/user/forget-password")
    public void forgetPassword(@RequestParam String email) {
        scaffPasswordResetTokensService.prepareAndSendToken(email);
    }

    @PutMapping("/user/reset-password")
    public void resetPassword(@RequestBody ScaffResetPasswordPutRequest request) {
        scaffPasswordResetTokensService.resetPassword(request.getToken(), request.getPasswordHash());
    }

    @PostMapping("/send/email-confirmation-link")
    public void sendConfirmationEmail() {
        scaffEmailConfirmService.sendEmailConfirmationLink();
    }

    @GetMapping("/notifications/count/unseen")
    public ScaffUnseenNotificationsCountRequestGetResponse getUnseenNotifications() {

        ScaffUserData user = scaffUsersService.getLoggedUser();
        if (user == null) {
            throw new IllegalArgumentException("You must be logged to get your unseen notifications count");
        }

        return new ScaffUnseenNotificationsCountRequestGetResponse(scaffNotificationsService.unseenNotificationsCount(user));
    }

    @GetMapping("/notifications")
    public ScaffNotificationsRequestGetResponse getNotifications() {
        ScaffUserData user = scaffUsersService.getLoggedUser();
        if (user == null) {
            throw new IllegalArgumentException("You must be logged to get your notifications");
        }

        List<ScaffNotificationData> notifications = scaffNotificationsService.find(new ScaffNotificationsFilter(false, user,
                ScaffNotificationType.PLATFORM, 10L));
        scaffNotificationsService.setNotificationsSeen(notifications);
        return new ScaffNotificationsRequestGetResponse(notificationsToResponses(notifications));
    }

    private List<ScaffNotificationGetResponse> notificationsToResponses(List<ScaffNotificationData> notifications) {

        List<ScaffNotificationGetResponse> responses = new ArrayList<>();

        for (ScaffNotificationData notification : notifications) {

            LocalDateTime dateTime = notification.getCreateDatetime();

            String createDatetime = dateTime.getHour() + ":" + dateTime.getMinute() + " " + dateTime.getDayOfMonth() + "." + dateTime.getMonthValue() + "." + dateTime.getYear();
            String title = notification.getTitle();
            String content = notification.getContent();
            String link = notification.getLink();
            boolean seen = notification.getSeenDatetime() == null;

            responses.add(new ScaffNotificationGetResponse(createDatetime, title, content, link, seen));
        }

        return responses;
    }

    @PostMapping("/trace")
    public void trace(@RequestBody ScaffTracePostRequest request, HttpServletRequest httpServletRequest) {
        scaffTraceService.trace(new ScaffTraceData(request.getWhat(), request.getValue(), request.getWho(),
                WebUtil.getClientIp(httpServletRequest), request.getBrowser(), request.getOperatingSystem()));
    }

}
