package pl.com.chrzanowski.scaffolding.logic;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.UserData;
import pl.com.chrzanowski.scaffolding.domain.UsersFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;

@Service
public class UserService {

    private UserJdbcRepository userJdbcRepository;
    private EmailService emailService;
    private UserAuthoritiesService userAuthoritiesService;

    public UserService(UserJdbcRepository userJdbcRepository, EmailService emailService,
                       UserAuthoritiesService userAuthoritiesService) {
        this.userJdbcRepository = userJdbcRepository;
        this.emailService = emailService;
        this.userAuthoritiesService = userAuthoritiesService;
    }


    public Long create(UserData user) {
        validate(user);
        String ip = prepareRegistrationIp(user.getRegistrationIp());
        String userAgent = prepareUserAgent(user.getRegistrationUserAgent());
        return userJdbcRepository.create(new UserData(user, ip, userAgent));
    }

    public UserData get(Long id) {
        return find(new UsersFilter(id)).get(0);
    }

    public List<UserData> find(UsersFilter filter) {
        return getUsers(userJdbcRepository.find(filter));
    }

    public List<UserData> findWithAuthorities(UsersFilter filter) {
        List<UserData> users = getUsers(userJdbcRepository.find(filter));
        List<UserData> usersWithRoles = new ArrayList<>();
        if (users == null) {
            usersWithRoles = null;
        } else {
            for (UserData user : users) {
                usersWithRoles.add(
                        new UserData(user, userAuthoritiesService.getAuthoritiesForUser(user)));
            }
        }
        return usersWithRoles;
    }

    public void update(UserData data) {
        validate(data);
        if (data.getAuthorities() != null) {
            userAuthoritiesService.deleteAuthorities(data);
            userAuthoritiesService.validateAndCreateAuthorityForUser(data, data.getAuthorities());
        } else {
            data = new UserData(data, userAuthoritiesService.getAuthoritiesForUser(data));
        }
        userJdbcRepository.update(data);
    }

    public void delete(Long id) {
        UserData userData = find(new UsersFilter(id)).get(0);
        userJdbcRepository.update(new UserData(userData, LocalDateTime.now()));
    }

    public void registerUser(UserData userCreateRequest) {

        validate(userCreateRequest);

        if (isEmailTaken(userCreateRequest.getLogin())) {
            addUserAuthorityToAccount(userCreateRequest);
        } else {
            createNewUserAccount(userCreateRequest);
        }
    }

    public UserData getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserData> list = getUsers(userJdbcRepository.find(new UsersFilter(authentication.getName())));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Boolean isLoggedUserAdmin() {
        return userAuthoritiesService.hasUserAuthority(getLoggedUser(), UserAuthority.ADMIN);
    }


    public Boolean isLoggedUser() {
        return userAuthoritiesService.hasUserAuthority(getLoggedUser(), UserAuthority.USER);
    }


    public void changeLoggedUsersPassword(String actualPasswordHash, String newPasswordHash) {
        UserData user = getLoggedUser();
        if (actualPasswordHash.equals(user.getPasswordHash())) {
            changePassword(user, newPasswordHash);
        } else {
            throw new IllegalArgumentException("Wrong actual password");
        }
    }

    public void changePasswordAdmin(Long id, String newPasswordHash) {
        UserData user = get(id);
        changePassword(user, newPasswordHash);
    }

    public void changePassword(UserData user, String newPasswordHash) {
        validatePasswordChange(newPasswordHash, user.getPasswordHash());
        update(new UserData(user, newPasswordHash));
        SecurityContextHolder.getContext().setAuthentication(null);
        emailService.sendAfterPasswordChangeMail(user);
    }

    public void updateLoggedUser(String language, Boolean newsletterAccepted) {
        UserData loggedUser = getLoggedUser();
        UserData updatedUser = new UserData(loggedUser, language, newsletterAccepted);
        update(updatedUser);
    }

    public List<UserData> findConfirmEmailNotificationRecipients() {
        return getUsers(userJdbcRepository.findConfirmEmailNotificationRecipients());
    }

    private String prepareUserAgent(String userAgent) {
        if (userAgent == null) {
            return null;
        } else if (userAgent.length() > 1000) {
            return "User agent too long to save it";
        } else {
            return userAgent;
        }
    }

    private String prepareRegistrationIp(String ip) {
        if (ip == null) {
            return null;
        } else if (ip.length() > 200) {
            return "Ip too long to save it";
        } else {
            return ip;
        }
    }

    private void validate(UserData data) {
        validateEmail(data.getLogin());
        validatePassword(data.getPasswordHash());
        validateRegulationAccept(data.getRegulationAccepted());
        validateNewsletterAccept(data.getNewsletterAccepted());
    }

    private void validateEmail(String email) {

        EmailUtil.validateEmail(email);

        if (email.length() > 255) {
            throw new IllegalArgumentException("Email too long, max 255 characters");
        }

    }

    private void validateIfEmailTaken(String email) {
        if (!userJdbcRepository.find(new UsersFilter(email)).isEmpty()) {
            throw new IllegalArgumentException("Email taken. Try again with other email.");
        }
    }

    private Boolean isEmailTaken(String email) {
        return !userJdbcRepository.find(new UsersFilter(email)).isEmpty();
    }

    private void validatePassword(String passwordHash) {
        if (passwordHash == null || passwordHash.equals("")) {
            throw new IllegalArgumentException("Password can't be empty");
        } else if (passwordHash.length() > 200) {
            throw new IllegalArgumentException("Password too long");
        }
    }

    private void validateRegulationAccept(Boolean regulationAccepted) {
        if (regulationAccepted == null || !regulationAccepted) {
            throw new IllegalArgumentException("You must accept regulation");
        }
    }

    private void validateNewsletterAccept(Boolean newsletterAccepted) {
        if (newsletterAccepted == null) {
            throw new IllegalArgumentException("Newsletter acceptation can't be empty");
        }
    }

    private void validatePasswordChange(String newPasswordHash, String oldPasswordHash) {
        if (oldPasswordHash.equals(newPasswordHash)) {
            throw new IllegalArgumentException("New password can't be the same as old password");
        }
    }


    private void createNewUserAccount(UserData userCreateRequest) {
        UserData user = new UserData(userCreateRequest.getLogin(),
                userCreateRequest.getPasswordHash(),
                userCreateRequest.getLanguage(),
                userCreateRequest.getRegulationAccepted(),
                userCreateRequest.getNewsletterAccepted(),
                userCreateRequest.getEnabled(),
                userCreateRequest.getRegistrationDatetime(),
                userCreateRequest.getRegistrationIp(),
                userCreateRequest.getRegistrationUserAgent(),
                userCreateRequest.getEmailConfirmed());
        Long userId = create(user);
        UserData createdUser = find(new UsersFilter(userId)).get(0);
        userAuthoritiesService.validateAndCreateAuthorityForUser(createdUser, userCreateRequest.getAuthorities());
        emailService.sendAfterRegistrationMail(createdUser, Locale.forLanguageTag(userCreateRequest.getLanguage()));
    }

    private void addUserAuthorityToAccount(UserData userCreateRequest) {
        UserData user = find(new UsersFilter(userCreateRequest.getLogin())).get(0);
        if (user.getLogin().equals(userCreateRequest.getLogin()) && user.getPasswordHash().equals(userCreateRequest.getPasswordHash())) {
//            customerAuthoritiesService.validateAndCreateAuthorityForCustomer(user, CustomerAuthority.USER);
            userAuthoritiesService.validateAndCreateAuthorityForUser(user, userCreateRequest.getAuthorities());
        } else {
            throw new IllegalArgumentException("Email or password incorrect");
        }
    }

    private List<UserData> getUsers(List<Map<String, Object>> data) {
        List<UserData> users = new ArrayList<>();

        for (Map<String, Object> row : data) {
            users.add(new UserData(
                    getLong(row, "id"),
                    getString(row, "first_name"),
                    getString(row, "second_name"),
                    getString(row, "login"),
                    getString(row, "password_hash"),
                    getString(row, "language"),
                    getBoolean(row, "regulation_accepted"),
                    getBoolean(row, "newsletter_accepted"),
                    getBoolean(row, "is_enabled"),
                    getDateTime(row, "registration_datetime"),
                    getString(row, "registration_ip"),
                    getString(row, "registration_user_agent"),
                    getBoolean(row, "email_confirmed")
            ));
        }
        return users;
    }

}
