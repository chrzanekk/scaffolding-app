package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.UsersFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UsersService {

    private UserJdbcRepository userJdbcRepository;
    private EmailService emailService;
    private UserAuthoritiesService userAuthoritiesService;

    public UsersService(UserJdbcRepository userJdbcRepository, EmailService emailService,
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
        return userJdbcRepository.find(filter);
    }

    public List<UserData> findWithAuthorities(UsersFilter filter) {
        List<UserData> customers = userJdbcRepository.find(filter);
        List<UserData> customersWithRoles = new ArrayList<>();
        if (customers == null) {
            customersWithRoles = null;
        } else {
            for (UserData customer : customers) {
                customersWithRoles.add(
                        new UserData(customer, userAuthoritiesService.getAuthoritiesForUser(customer)));
            }
        }
        return customersWithRoles;
    }

    public void update(UserData data) {
        validate(data);
        userAuthoritiesService.deleteAuthorities(data);
        userAuthoritiesService.validateAndCreateAuthorityForUser(data, data.getAuthorities());
        userJdbcRepository.update(data);
    }

    public void delete(Long id) {
        UserData userData = find(new UsersFilter(id)).get(0);
        userJdbcRepository.update(new UserData(userData, LocalDateTime.now()));
    }

    public void registerCustomer(UserData userCreateRequest) {

        validate(userCreateRequest);

        if (isEmailTaken(userCreateRequest.getLogin())) {
            addCustomerAuthorityToAccount(userCreateRequest);
        } else {
            createNewCustomerAccount(userCreateRequest);
        }
    }

    public UserData getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserData> list = userJdbcRepository.find(new UsersFilter(authentication.getName()));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Boolean isLoggedCustomerAdmin() {
        return userAuthoritiesService.hasUserAuthority(getLoggedUser(), UserAuthority.ADMIN);
    }


    public Boolean isLoggedCustomerUser() {
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

    public void updateLoggedCustomer(String language, Boolean newsletterAccepted) {
        UserData loggedUser = getLoggedUser();
        UserData updatedCustomer = new UserData(loggedUser, language, newsletterAccepted);
        update(updatedCustomer);
    }

    public List<UserData> findConfirmEmailNotificationRecipients() {
        return userJdbcRepository.findConfirmEmailNotificationRecipients();
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


    private void createNewCustomerAccount(UserData userCreateRequest) {
        UserData customer = new UserData(userCreateRequest.getLogin(),
                userCreateRequest.getPasswordHash(),
                userCreateRequest.getLanguage(),
                userCreateRequest.getRegulationAccepted(),
                userCreateRequest.getNewsletterAccepted(),
                userCreateRequest.getEnabled(),
                userCreateRequest.getRegistrationDatetime(),
                userCreateRequest.getRegistrationIp(),
                userCreateRequest.getRegistrationUserAgent(),
                userCreateRequest.getEmailConfirmed());
        Long userId = create(customer);
        UserData createdUser = find(new UsersFilter(userId)).get(0);
        userAuthoritiesService.validateAndCreateAuthorityForUser(createdUser, userCreateRequest.getAuthorities());
        emailService.sendAfterRegistrationMail(createdUser, Locale.forLanguageTag(userCreateRequest.getLanguage()));
    }

    private void addCustomerAuthorityToAccount(UserData userCreateRequest) {
        UserData customer = find(new UsersFilter(userCreateRequest.getLogin())).get(0);
        if (customer.getLogin().equals(userCreateRequest.getLogin()) && customer.getPasswordHash().equals(userCreateRequest.getPasswordHash())) {
//            customerAuthoritiesService.validateAndCreateAuthorityForCustomer(customer, CustomerAuthority.USER);
            userAuthoritiesService.validateAndCreateAuthorityForUser(customer, userCreateRequest.getAuthorities());
        } else {
            throw new IllegalArgumentException("Email or password incorrect");
        }
    }


}
