package pl.com.chrzanowski.scaffolding.logic.scaffoldingapp;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffUserData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehicleData;
import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffVehiclesFilter;

import java.sql.SQLException;
import java.util.List;

@Service
public class ScaffVehiclesService {

    private ScaffVehiclesJdbcRepository scaffVehiclesJdbcRepository;
    private ScaffUsersService usersService;
    private ScaffUserAuthoritiesService userAuthoritiesService;

    public ScaffVehiclesService(ScaffVehiclesJdbcRepository scaffVehiclesJdbcRepository,
                                ScaffUsersService usersService, ScaffUserAuthoritiesService userAuthoritiesService) {
        this.scaffVehiclesJdbcRepository = scaffVehiclesJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
    }

    public List<ScaffVehicleData> find(ScaffVehiclesFilter filter) throws SQLException {
        return scaffVehiclesJdbcRepository.find(filter);
    }
//to chyba nie dziala poprawnie - do sprawdzenia
    public ScaffVehicleData findById(ScaffVehiclesFilter filter) throws SQLException {
        return scaffVehiclesJdbcRepository.get(filter);
    }


    public Boolean hasLoggedUserPermissionToVehicleManagement() {

        ScaffUserData loggedUser = usersService.getLoggedUser();

        return userAuthoritiesService.hasUserAuthority(loggedUser, ScaffUserAuthority.ADMIN);

    }

    public Long add(ScaffVehicleData data) {
        return scaffVehiclesJdbcRepository.create(data);
    }

    public void update(ScaffVehicleData data) {
        scaffVehiclesJdbcRepository.update(data);
    }

}
