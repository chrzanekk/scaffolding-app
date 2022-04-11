package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.serviceactiontypes.*;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypesFilter;
import pl.com.chrzanowski.scaffolding.logic.IServiceActonTypes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminServiceActionTypes {
    private IServiceActonTypes serviceActonTypes;

    public ScaffoldingEndpointAdminServiceActionTypes(IServiceActonTypes serviceActonTypes) {
        this.serviceActonTypes = serviceActonTypes;
    }

    @GetMapping(path = "/service-action-types", produces = "application/json; charset=UTF-8")
    public ServiceActionTypesRequestGetResponse serviceActionTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionTypeData> serviceActionTypes =
                serviceActonTypes.find(new ServiceActionTypesFilter(page, pageSize,false));
        return new ServiceActionTypesRequestGetResponse(actionTypesToResponse(serviceActionTypes));
    }

    @GetMapping(path = "/removed-service-action-types", produces = "application/json; charset=UTF-8")
    public ServiceActionTypesRequestGetResponse removedServiceActionTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ServiceActionTypeData> serviceActionTypes =
                serviceActonTypes.find(new ServiceActionTypesFilter(page, pageSize,true));
        return new ServiceActionTypesRequestGetResponse(actionTypesToResponse(serviceActionTypes));
    }

    @GetMapping(path = "/service-action-type/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionTypeRequestGetResponse serviceActionTypesById(
            @PathVariable Long id) {
        ServiceActionTypeData serviceActionType =
                serviceActonTypes.find(new ServiceActionTypesFilter(id, false)).get(0);
        return new ServiceActionTypeRequestGetResponse(actionTypeToResponse(serviceActionType));
    }

    @GetMapping(path = "/removed-service-action-type/{id}", produces = "application/json; charset=UTF-8")
    public ServiceActionTypeRequestGetResponse removedServiceActionTypesById(
            @PathVariable Long id) {
        ServiceActionTypeData serviceActionType = serviceActonTypes.find(new ServiceActionTypesFilter(id,true)).get(0);
        return new ServiceActionTypeRequestGetResponse(actionTypeToResponse(serviceActionType));
    }

    @PostMapping(path = "/service-action-type", consumes = "application/json; charset=UTF-8")
    public void addServiceActionType(@RequestBody ServiceActionTypesPostRequest request) {
        serviceActonTypes.add(new ServiceActionTypeData(request.getName()));
    }

    @PutMapping(path = "/service-action-type/{id}", consumes = "application/json; charset=UTF-8")
    public void updateServiceActionType(@PathVariable Long id,
                                        @RequestBody ServiceActionTypesPutRequest request) {
        serviceActonTypes.update(new ServiceActionTypeData(id, request.getName(),null));
    }

    @PutMapping(path = "/service-action-type-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeServiceActionType(@PathVariable Long id,
                                        @RequestBody ServiceActionTypesPutRequest request) {
        serviceActonTypes.update(new ServiceActionTypeData(id, request.getName(),request.getRemoveDate()));
    }




    private List<ServiceActionTypesGetResponse> actionTypesToResponse(List<ServiceActionTypeData> actionTypes) {
        List<ServiceActionTypesGetResponse> list = new ArrayList<>();
        for (ServiceActionTypeData data : actionTypes) {
            list.add(new ServiceActionTypesGetResponse(
                    data.getId(),
                    data.getName()
            ));
        }
        return list;
    }

    private ServiceActionTypesGetResponse actionTypeToResponse(ServiceActionTypeData actionType) {
        return new ServiceActionTypesGetResponse(actionType.getId(), actionType.getName());
    }
}
