package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionsFilter;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getString;

@Service
public class ServiceActionsService implements IServiceActions {

    private ServiceActionsJdbcRepository serviceActionsJdbcRepository;
    private UserService usersService;
    private UserAuthoritiesService userAuthoritiesService;

    public ServiceActionsService(ServiceActionsJdbcRepository serviceActionsJdbcRepository, UserService usersService, UserAuthoritiesService userAuthoritiesService) {
        this.serviceActionsJdbcRepository = serviceActionsJdbcRepository;
        this.usersService = usersService;
        this.userAuthoritiesService = userAuthoritiesService;
    }

    public List<ServiceActionsData> find(ServiceActionsFilter filter) {
        return getActions(serviceActionsJdbcRepository.find(filter));
    }

    public ServiceActionsData findById(ServiceActionsFilter filter) {
        return getActions(serviceActionsJdbcRepository.find(filter)).get(0);
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

    public void delete(ServiceActionsData data) {
    }


    private List<ServiceActionsData> getActions(List<Map<String, Object>> data) {

        List<ServiceActionsData> list = new ArrayList<>();

        for (Map<String, Object> row : data) {
            list.add(new ServiceActionsData(
                    getLong(row, "id"),
                    getLong(row, "vehicle_id"),
                    getInteger(row, "car_mileage"),
                    getDate(row, "service_date"),
                    getString(row, "invoice_no"),
                    getLong(row, "workshop_id"),
                    getLong(row, "service_action_type_id"),
                    getString(row, "action_type"),
                    getString(row, "workshop"),
                    new WorkshopsData(
                            getString(row,"workshop"),
                            getString(row,"tax_number"),
                            getString(row,"street"),
                            getString(row,"building_number"),
                            getString(row,"apartment_number"),
                            getString(row,"postal_code"),
                            getString(row,"city")),
                    getString(row, "description")
            ));
        }
        return list;
    }

}
