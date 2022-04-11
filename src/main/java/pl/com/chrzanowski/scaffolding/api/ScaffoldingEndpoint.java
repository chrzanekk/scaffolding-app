package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.notofiactions.NotificationGetResponse;
import pl.com.chrzanowski.scaffolding.api.notofiactions.NotificationsRequestGetResponse;
import pl.com.chrzanowski.scaffolding.api.notofiactions.TracePostRequest;
import pl.com.chrzanowski.scaffolding.api.notofiactions.UnseenNotificationsCountRequestGetResponse;
import pl.com.chrzanowski.scaffolding.api.users.ChangePasswordRequest;
import pl.com.chrzanowski.scaffolding.api.users.LoggedUserPutRequest;
import pl.com.chrzanowski.scaffolding.api.users.ResetPasswordPutRequest;
import pl.com.chrzanowski.scaffolding.api.users.UserPostRequest;
import pl.com.chrzanowski.scaffolding.domain.NotificationData;
import pl.com.chrzanowski.scaffolding.domain.NotificationsFilter;
import pl.com.chrzanowski.scaffolding.domain.TraceData;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.logic.email.EmailConfirmService;
import pl.com.chrzanowski.scaffolding.logic.notifications.NotificationType;
import pl.com.chrzanowski.scaffolding.logic.notifications.NotificationsService;
import pl.com.chrzanowski.scaffolding.logic.trace.TraceService;
import pl.com.chrzanowski.scaffolding.logic.user.PasswordResetTokensService;
import pl.com.chrzanowski.scaffolding.logic.user.UserAuthority;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;
import pl.com.chrzanowski.scaffolding.logic.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/scaffolding")
public class ScaffoldingEndpoint {

    private UserService userService;
    private PasswordResetTokensService passwordResetTokensService;
    private TraceService traceService;
    private EmailConfirmService emailConfirmService;
    private NotificationsService notificationsService;

    public ScaffoldingEndpoint(UserService userService, PasswordResetTokensService passwordResetTokensService, TraceService traceService, EmailConfirmService emailConfirmService, NotificationsService notificationsService) {
        this.userService = userService;
        this.passwordResetTokensService = passwordResetTokensService;
        this.traceService = traceService;
        this.emailConfirmService = emailConfirmService;
        this.notificationsService = notificationsService;
    }

    @PostMapping("/register-user")
    public void register(@RequestBody UserPostRequest request, HttpServletRequest httpServletRequest) {
        UserData userCreateRequest = new UserData(
                request.getFirstName(),
                request.getLastName(),
                request.getLogin(),
                request.getPasswordHash(),
                request.getLanguage(),
                request.getRegulationAccepted(),
                request.getNewsletterAccepted(),
                request.getIsEnabled(),
                request.getIsEmailConfirmed(),
                new String[] {UserAuthority.USER.getCode()},
                httpServletRequest
                );
        userService.registerUser(userCreateRequest);
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changeLoggedUsersPassword(request.getActualPasswordHash(), request.getNewPasswordHash());
    }

    @PutMapping("/logged-user")
    public void updateLoggedUser(@RequestBody LoggedUserPutRequest request) {
        UserData loggedUser = userService.getLoggedUser();

        if (loggedUser == null) {
            throw new IllegalArgumentException("Your are logged out. Please log in and retry");
        }
        userService.updateLoggedUser(request.getLanguage(), request.getNewsletterAccepted());
    }

    @PostMapping("/user/forget-password")
    public void forgetPassword(@RequestParam String email) {
        passwordResetTokensService.prepareAndSendToken(email);
    }

    @PutMapping("/user/reset-password")
    public void resetPassword(@RequestBody ResetPasswordPutRequest request) {
        passwordResetTokensService.resetPassword(request.getToken(), request.getPasswordHash());
    }

    @PostMapping("/send/email-confirmation-link")
    public void sendConfirmationEmail() {
        emailConfirmService.sendEmailConfirmationLink();
    }

    @GetMapping("/notifications/count/unseen")
    public UnseenNotificationsCountRequestGetResponse getUnseenNotifications() {

        UserData user = userService.getLoggedUser();
        if (user == null) {
            throw new IllegalArgumentException("You must be logged to get your unseen notifications count");
        }

        return new UnseenNotificationsCountRequestGetResponse(notificationsService.unseenNotificationsCount(user));
    }

    @GetMapping("/notifications")
    public NotificationsRequestGetResponse getNotifications() {
        UserData user = userService.getLoggedUser();
        if (user == null) {
            throw new IllegalArgumentException("You must be logged to get your notifications");
        }

        List<NotificationData> notifications = notificationsService.find(new NotificationsFilter(false, user,
                NotificationType.PLATFORM, 10L));
        notificationsService.setNotificationsSeen(notifications);
        return new NotificationsRequestGetResponse(notificationsToResponses(notifications));
    }

    private List<NotificationGetResponse> notificationsToResponses(List<NotificationData> notifications) {

        List<NotificationGetResponse> responses = new ArrayList<>();

        for (NotificationData notification : notifications) {

            LocalDateTime dateTime = notification.getCreateDatetime();

            String createDatetime = dateTime.getHour() + ":" + dateTime.getMinute() + " " + dateTime.getDayOfMonth() + "." + dateTime.getMonthValue() + "." + dateTime.getYear();
            String title = notification.getTitle();
            String content = notification.getContent();
            String link = notification.getLink();
            boolean seen = notification.getSeenDatetime() == null;

            responses.add(new NotificationGetResponse(createDatetime, title, content, link, seen));
        }

        return responses;
    }

    @PostMapping("/trace")
    public void trace(@RequestBody TracePostRequest request, HttpServletRequest httpServletRequest) {
        traceService.trace(new TraceData(request.getWhat(), request.getValue(), request.getWho(),
                WebUtil.getClientIp(httpServletRequest), request.getBrowser(), request.getOperatingSystem()));
    }

}
