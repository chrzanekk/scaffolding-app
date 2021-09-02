package pl.com.chrzanowski.scaffolding.api.scaffolding;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.courseplatform.ChangePasswordRequest;
import pl.com.chrzanowski.scaffolding.api.courseplatform.CustomerPutRequest;
import pl.com.chrzanowski.scaffolding.domain.courseplatform.CustomerData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffCreateNotificationsParameters;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffStatisticService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.ScaffUsersService;
import pl.com.chrzanowski.scaffolding.logic.scaffoldingapp.notifications.ScaffNotificationsFromPanelService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdmin {

    private ScaffUsersService scaffUsersService;
    private ScaffNotificationsFromPanelService notificationsFromPanelService;
    private ScaffStatisticService scaffStatisticService;

    public ScaffoldingEndpointAdmin(ScaffUsersService scaffUsersService, ScaffNotificationsFromPanelService notificationsFromPanelService, ScaffStatisticService scaffStatisticService) {
        this.scaffUsersService = scaffUsersService;
        this.notificationsFromPanelService = notificationsFromPanelService;
        this.scaffStatisticService = scaffStatisticService;
    }

    @GetMapping("/users")
    public List<ScaffUserGetResponse> getCustomers(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "login", required = false) String loginLike
    ) {
        List<ScaffUserData> users = scaffUsersService.find(new ScaffUsersFilter(loginLike, page, pageSize
                ));
        return customersToResponses(users);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody ScaffUserPostRequest request, HttpServletRequest httpServletRequest) {
        scaffUsersService.registerUser(
                new ScaffUserData(
                        request.getLogin(),
                        request.getPasswordHash(),
                        request.getLanguage(),
                        request.getRegulationAccepted(),
                        request.getNewsletterAccepted(),
                        request.getIsEnabled(),
                        request.getIsEmailConfirmed(),
                        request.getAuthorities(),
                        httpServletRequest));
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody ScaffUserPutRequest request) {
        ScaffUserData data = scaffUsersService.get(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        scaffUsersService.update(new ScaffUserData(id,
                request.getLogin(),
                data.getPasswordHash(),
                request.getLanguage(),
                request.getRegulationAccepted(),
                request.getNewsletterAccepted(),
                request.getIsEnabled(),
                request.getIsEmailConfirmed(),
                LocalDateTime.parse(request.getRegistrationDatetime(), formatter),
                request.getAuthorities()));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        scaffUsersService.delete(id);
    }

    @PutMapping("/user-change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        scaffUsersService.changePasswordAdmin(id, request.getNewPasswordHash());
    }

    @PostMapping("/notifications")
    public void createNotifications(@RequestBody ScaffNotificationsPostRequest request) {
        notificationsFromPanelService.createNotifications(new ScaffCreateNotificationsParameters(request));
    }








    private List<ScaffUserGetResponse> customersToResponses(List<ScaffUserData> users) {
        List<ScaffUserGetResponse> list = new ArrayList<>();
        for (ScaffUserData user : users) {
            list.add(new ScaffUserGetResponse(user));
        }
        return list;
    }
}
