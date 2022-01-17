package pl.com.chrzanowski.scaffolding.api;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.domain.*;
import pl.com.chrzanowski.scaffolding.logic.*;
import pl.com.chrzanowski.scaffolding.logic.notifications.NotificationsFromPanelService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdmin {

    private UserService userService;
    private NotificationsFromPanelService notificationsFromPanelService;
    private IFuelTypes fuelTypes;
    private MarketingService marketingService;

    public ScaffoldingEndpointAdmin(UserService userService,
                                    NotificationsFromPanelService notificationsFromPanelService,
                                    IFuelTypes fuelTypes,
                                    MarketingService marketingService) {
        this.userService = userService;
        this.notificationsFromPanelService = notificationsFromPanelService;
        this.fuelTypes = fuelTypes;
        this.marketingService = marketingService;
    }

    @GetMapping("/users")
    public UsersRequestGetResponse getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "login", required = false) String loginLike
    ) {
        List<UserData> users = userService.find(new UsersFilter(loginLike, page, pageSize
        ));
        return new UsersRequestGetResponse(usersToResponses(users));
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserPostRequest request, HttpServletRequest httpServletRequest) {
        userService.registerUser(
                new UserData(
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
                        httpServletRequest));
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserPutRequest request) {
        UserData data = userService.get(id);
        userService.update(new UserData(id,
                request.getFirstName(),
                request.getLastName(),
                request.getLogin(),
                data.getPasswordHash(),
                request.getLanguage(),
                request.getRegulationAccepted(),
                request.getNewsletterAccepted(),
                request.getIsEnabled(),
                request.getIsEmailConfirmed(),
                request.getRegistrationDatetime(),
                request.getAuthorities(),
                data.getRegistrationIp(),
                data.getRegistrationUserAgent()));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/user-change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        userService.changePasswordAdmin(id, request.getNewPasswordHash());
    }

    @PostMapping("/notifications")
    public void createNotifications(@RequestBody NotificationsPostRequest request) {
        notificationsFromPanelService.createNotifications(new CreateNotificationsParameters(request));
    }

    @PostMapping("/marketing/newsletter")
    public void newsletter(@RequestBody NewsletterData request) {
        marketingService.sendNewsletter(request);
    }

    @GetMapping(path = "/fuel-types", produces = "application/json; charset=UTF-8")
    public FuelTypeRequestGetResponse fuelTypes(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<FuelTypeData> fuelTypes = this.fuelTypes.find(new FuelTypeFilter(page, pageSize));
        return new FuelTypeRequestGetResponse(fuelTypeToResponse(fuelTypes));
    }




    private List<UserGetResponse> usersToResponses(List<UserData> users) {
        List<UserGetResponse> list = new ArrayList<>();
        for (UserData user : users) {
            list.add(new UserGetResponse(user, parseDateTime(user.getRegistrationDatetime())));
        }
        return list;
    }

    private List<FuelTypeGetResponse> fuelTypeToResponse(List<FuelTypeData> fuelTypeData) {
        List<FuelTypeGetResponse> list = new ArrayList<>();
        for (FuelTypeData type : fuelTypeData) {
            list.add(new FuelTypeGetResponse(type));
        }
        return list;
    }

    private String parseDateTime(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        return date.toString();
    }

}
