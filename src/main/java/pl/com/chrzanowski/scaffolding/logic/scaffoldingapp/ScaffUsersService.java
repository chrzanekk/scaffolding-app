package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUsersFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ScaffUsersService {

    private ScaffUserJdbcRepository scaffUserJdbcRepository;
    private ScaffEmailService scaffEmailService;
    private ScaffUserAuthoritiesService scaffUserAuthoritiesService;

    public ScaffUsersService(ScaffUserJdbcRepository scaffUserJdbcRepository, ScaffEmailService scaffEmailService,
                             ScaffUserAuthoritiesService scaffUserAuthoritiesService) {
        this.scaffUserJdbcRepository = scaffUserJdbcRepository;
        this.scaffEmailService = scaffEmailService;
        this.scaffUserAuthoritiesService = scaffUserAuthoritiesService;
    }


    public Long create(ScaffUserData user) {
        validate(user);
        String ip = prepareRegistrationIp(user.getRegistrationIp());
        String userAgent = prepareUserAgent(user.getRegistrationUserAgent());
        return scaffUserJdbcRepository.create(new ScaffUserData(user, ip, userAgent));
    }

    public ScaffUserData get(Long id) {
        return find(new ScaffUsersFilter(id)).get(0);
    }

    public List<ScaffUserData> find(ScaffUsersFilter filter) {
        return scaffUserJdbcRepository.find(filter);
    }

    public List<ScaffUserData> findWithAuthorities(ScaffUsersFilter filter) {
        List<ScaffUserData> users = scaffUserJdbcRepository.find(filter);
        List<ScaffUserData> usersWithRoles = new ArrayList<>();
        if (users == null) {
            usersWithRoles = null;
        } else {
            for (ScaffUserData customer : users) {
                usersWithRoles.add(
                        new ScaffUserData(customer, scaffUserAuthoritiesService.getAuthoritiesForUser(customer)));
            }
        }
        return usersWithRoles;
    }

    public void update(ScaffUserData data) {
        validate(data);
        if(data.getAuthorities() != null) {
            scaffUserAuthoritiesService.deleteAuthorities(data);
            scaffUserAuthoritiesService.validateAndCreateAuthorityForUser(data, data.getAuthorities());
        } else {
            data = new ScaffUserData(data, scaffUserAuthoritiesService.getAuthoritiesForUser(data));
        }
        scaffUserJdbcRepository.update(data);
    }

    public void delete(Long id) {
        ScaffUserData scaffUserData = find(new ScaffUsersFilter(id)).get(0);
        scaffUserJdbcRepository.update(new ScaffUserData(scaffUserData, LocalDateTime.now()));
    }

    public void registerUser(ScaffUserData userCreateRequest) {

        validate(userCreateRequest);

        if (isEmailTaken(userCreateRequest.getLogin())) {
            addUserAuthorityToAccount(userCreateRequest);
        } else {
            createNewUserAccount(userCreateRequest);
        }
    }

    public ScaffUserData getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ScaffUserData> list = scaffUserJdbcRepository.find(new ScaffUsersFilter(authentication.getName()));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Boolean isLoggedUserAdmin() {
        return scaffUserAuthoritiesService.hasUserAuthority(getLoggedUser(), ScaffUserAuthority.ADMIN);
    }


    public Boolean isLoggedUser() {
        return scaffUserAuthoritiesService.hasUserAuthority(getLoggedUser(), ScaffUserAuthority.USER);
    }


    public void changeLoggedUsersPassword(String actualPasswordHash, String newPasswordHash) {
        ScaffUserData user = getLoggedUser();
        if (actualPasswordHash.equals(user.getPasswordHash())) {
            changePassword(user, newPasswordHash);
        } else {
            throw new IllegalArgumentException("Wrong actual password");
        }
    }

    public void changePasswordAdmin(Long id, String newPasswordHash) {
        ScaffUserData user = get(id);
        changePassword(user, newPasswordHash);
    }

    public void changePassword(ScaffUserData user, String newPasswordHash) {
        validatePasswordChange(newPasswordHash, user.getPasswordHash());
        update(new ScaffUserData(user, newPasswordHash));
        SecurityContextHolder.getContext().setAuthentication(null);
        scaffEmailService.sendAfterPasswordChangeMail(user);
    }

    public void updateLoggedUser(String language, Boolean newsletterAccepted) {
        ScaffUserData loggedUser = getLoggedUser();
        ScaffUserData updatedUser = new ScaffUserData(loggedUser, language, newsletterAccepted);
        update(updatedUser);
    }

    public List<ScaffUserData> findConfirmEmailNotificationRecipients() {
        return scaffUserJdbcRepository.findConfirmEmailNotificationRecipients();
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

    private void validate(ScaffUserData data) {
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
        if (!scaffUserJdbcRepository.find(new ScaffUsersFilter(email)).isEmpty()) {
            throw new IllegalArgumentException("Email taken. Try again with other email.");
        }
    }

    private Boolean isEmailTaken(String email) {
        return !scaffUserJdbcRepository.find(new ScaffUsersFilter(email)).isEmpty();
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


    private void createNewUserAccount(ScaffUserData userCreateRequest) {
        ScaffUserData user = new ScaffUserData(userCreateRequest.getLogin(),
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
        ScaffUserData createdUser = find(new ScaffUsersFilter(userId)).get(0);
        scaffUserAuthoritiesService.validateAndCreateAuthorityForUser(createdUser, userCreateRequest.getAuthorities());
        scaffEmailService.sendAfterRegistrationMail(createdUser, Locale.forLanguageTag(userCreateRequest.getLanguage()));
    }

    private void addUserAuthorityToAccount(ScaffUserData userCreateRequest) {
        ScaffUserData user = find(new ScaffUsersFilter(userCreateRequest.getLogin())).get(0);
        if (user.getLogin().equals(userCreateRequest.getLogin()) && user.getPasswordHash().equals(userCreateRequest.getPasswordHash())) {
//            customerAuthoritiesService.validateAndCreateAuthorityForCustomer(user, CustomerAuthority.USER);
            scaffUserAuthoritiesService.validateAndCreateAuthorityForUser(user, userCreateRequest.getAuthorities());
        } else {
            throw new IllegalArgumentException("Email or password incorrect");
        }
    }


}
