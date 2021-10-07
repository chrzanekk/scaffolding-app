package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.UserData;

import java.util.List;

@Service
public class ServiceActionsService {

    private ServiceActionsJdbcRepository serviceActionsJdbcRepository;
    private ScaffUsersService usersService;
    private UserAuthoritiesService userAuthoritiesService;

    public ServiceActionsService(ServiceActionsJdbcRepository serviceActionsJdbcRepository, ScaffUsersService usersService, UserAuthoritiesService userAuthoritiesService) {
        this.serviceActionsJdbcRepository = serviceActionsJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
    }

    public List<ServiceActionsData> find(ServiceActionsFilter filter) {
        return serviceActionsJdbcRepository.find(filter);
    }

    public ServiceActionsData findById(ServiceActionsFilter filter) {
        return serviceActionsJdbcRepository.get(filter);
    }

    public Boolean hasLoggedUserPermissionToActionsManagement() {

        UserData loggedUser = usersService.getLoggedUser();

        return userAuthoritiesService.hasUserAuthority(loggedUser, UserAuthority.ADMIN);

    }

    public Long add(ServiceActionsData data) {
        return serviceActionsJdbcRepository.create(data);
    }

    public void update(ServiceActionsData data) {
            serviceActionsJdbcRepository.update(data);
    }
}
