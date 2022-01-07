package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopServiceTypeFilter;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsFilter;
import pl.com.chrzanowski.scaffolding.logic.WorkshopServiceTypeService;
import pl.com.chrzanowski.scaffolding.logic.WorkshopsService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminWorkshops {
    private WorkshopsService workshopsService;
    private WorkshopServiceTypeService workshopServiceTypeService;

    public ScaffoldingEndpointAdminWorkshops(WorkshopsService workshopsService, WorkshopServiceTypeService workshopServiceTypeService) {
        this.workshopsService = workshopsService;
        this.workshopServiceTypeService = workshopServiceTypeService;
    }

    @GetMapping(path = "/workshops", produces = "application/json; charset=UTF-8")
    public WorkshopsRequestGetResponse workshops(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) throws SQLException {
        List<WorkshopsData> workshops = workshopsService.findWithActionTypes(workshopsService.find(new WorkshopsFilter(name, city, page,
                pageSize, false)));
        return new WorkshopsRequestGetResponse(workshopsToResponse(workshops));
    }

    @GetMapping(path = "/removed-workshops", produces = "application/json; charset=UTF-8")
    public WorkshopsRequestGetResponse removedWorkshops(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<WorkshopsData> workshops = workshopsService.findWithActionTypes(workshopsService.find(new WorkshopsFilter(name, city, page,
                pageSize, true)));
        return new WorkshopsRequestGetResponse(workshopsToResponse(workshops));
    }

    @GetMapping(path = "/workshop/{id}", produces = "application/json; charset=UTF-8")
    public WorkshopRequestGetResponse workshopById(
            @PathVariable Long id) {
        WorkshopsData workshop = workshopsService.findWithActionTypes(workshopsService.find(new WorkshopsFilter(id))).get(0);
        return new WorkshopRequestGetResponse(workshopToResponse(workshop));
    }

    @PostMapping(path = "/workshop", consumes = "application/json; charset=UTF-8")
    public void addWorkshop(@RequestBody WorkshopPostRequest request) {
        workshopsService.add(new WorkshopsData(
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getActionTypes()));
    }

    @PutMapping(path = "/workshop/{id}", consumes = "application/json; charset=UTF-8")
    public void updateWorkshop(@PathVariable Long id, @RequestBody WorkshopPutRequest request) {
        workshopsService.update(new WorkshopsData(
                id,
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getActionTypes(),
                null));
    }

    @PutMapping(path = "/workshop-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeWorkshop(@PathVariable Long id, @RequestBody WorkshopPutRequest request) {
        workshopsService.update(new WorkshopsData(
                id,
                request.getName(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getActionTypes(),
                request.getRemoveDate()));
    }

    @GetMapping(path = "/workshop-service-types", produces = "application/json; charset=UTF-8")
    public WorkshopServiceTypesRequestGetResponse serviceTypes(@RequestParam(name = "workshop_id") Long workshopId) {
        List<WorkshopServiceTypeData> workshopServiceTypes = workshopServiceTypeService.find(new WorkshopServiceTypeFilter(workshopId));
        return new WorkshopServiceTypesRequestGetResponse(workshopServicesToResponse(workshopServiceTypes));
    }

    private List<WorkshopGetResponse> workshopsToResponse(List<WorkshopsData> workshops) {
        List<WorkshopGetResponse> list = new ArrayList<>();
        for (WorkshopsData workshop : workshops) {
            list.add(new WorkshopGetResponse(
                    workshop.getId(),
                    workshop.getName(),
                    workshop.getTaxNumber(),
                    workshop.getStreet(),
                    workshop.getBuildingNo(),
                    workshop.getApartmentNo(),
                    workshop.getPostalCode(),
                    workshop.getCity(),
                    workshop.getActionTypes(),
                    workshop.getActionTypesList(),
                    parseDateTime(workshop.getRemoveDate()))
            );
        }
        return list;
    }

    private WorkshopGetResponse workshopToResponse(WorkshopsData workshop) {
        return new WorkshopGetResponse(
                workshop.getId(),
                workshop.getName(),
                workshop.getTaxNumber(),
                workshop.getStreet(),
                workshop.getBuildingNo(),
                workshop.getApartmentNo(),
                workshop.getPostalCode(),
                workshop.getCity(),
                workshop.getActionTypes(),
                workshop.getActionTypesList(),
                parseDateTime(workshop.getRemoveDate())
        );
    }

    private List<WorkshopServiceTypesGetResponse> workshopServicesToResponse(List<WorkshopServiceTypeData> workshopServices) {
        List<WorkshopServiceTypesGetResponse> list = new ArrayList<>();
        for (WorkshopServiceTypeData data : workshopServices) {
            list.add(new WorkshopServiceTypesGetResponse(
                    data.getId(),
                    data.getWorkshopId(),
                    data.getServiceActionTypeId(),
                    data.getServiceActionName()
            ));
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
