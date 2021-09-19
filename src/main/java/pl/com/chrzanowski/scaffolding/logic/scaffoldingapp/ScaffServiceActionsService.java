package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;

import java.util.List;

@Service
public class ScaffServiceActionsService {

    private ScaffServiceActionsJdbcRepository serviceActionsJdbcRepository;
    private ScaffUsersService usersService;
    private ScaffUserAuthoritiesService userAuthoritiesService;

    public ScaffServiceActionsService(ScaffServiceActionsJdbcRepository serviceActionsJdbcRepository, ScaffUsersService usersService, ScaffUserAuthoritiesService userAuthoritiesService) {
        this.serviceActionsJdbcRepository = serviceActionsJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
    }

    public List<ScaffServiceActionsData> find(ScaffServiceActionsFilter filter) {
        return serviceActionsJdbcRepository.find(filter);
    }

    public ScaffServiceActionsData findById(ScaffServiceActionsFilter filter) {
        return serviceActionsJdbcRepository.get(filter);
    }

    public Boolean hasLoggedUserPermissionToActionsManagement() {

        ScaffUserData loggedUser = usersService.getLoggedUser();

        return userAuthoritiesService.hasUserAuthority(loggedUser, ScaffUserAuthority.ADMIN);

    }
}
