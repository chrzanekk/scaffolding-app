package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.contractortypes.*;
import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeData;
import pl.com.chrzanowski.scaffolding.domain.contractortypes.ContractorTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.IContractorTypes;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminContractorTypes {

    private IContractorTypes contractorTypesService;

    public ScaffoldingEndpointAdminContractorTypes(IContractorTypes contractorTypesService) {
        this.contractorTypesService = contractorTypesService;
    }

    @GetMapping(path = "/contractor-types", produces = "application/json; charset=UTF-8")
    public ContractorTypesRequestGetResponse contractorTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ContractorTypeData> contractorTypesList = contractorTypesService.find(new ContractorTypeFilter(page, pageSize,
                false));
        return new ContractorTypesRequestGetResponse(contractorTypesToResponse(contractorTypesList));
    }

    @GetMapping(path = "/removed-contractor-types", produces = "application/json; charset=UTF-8")
    public ContractorTypesRequestGetResponse removedContractorTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ContractorTypeData> contractorTypesList = contractorTypesService.find(new ContractorTypeFilter(page, pageSize,
                true));
        return new ContractorTypesRequestGetResponse(contractorTypesToResponse(contractorTypesList));
    }

    @GetMapping(path = "/contractor-type/{id}", produces = "application/json; charset=UTF-8")
    public ContractorTypeRequestGetResponse contractorTypeById(
            @PathVariable Long id) {
        ContractorTypeData contractorType = contractorTypesService.find(new ContractorTypeFilter(id, false)).get(0);
        return new ContractorTypeRequestGetResponse(contractorTypeToResponse(contractorType));
    }

    @PostMapping(path = "/contractor-type", consumes = "application/json; charset=UTF-8")
    public void addContractorType(@RequestBody ContractorTypePostRequest request) {
        contractorTypesService.add(new ContractorTypeData(request.getName(), request.getCreateUserId()));
    }

    @PutMapping(path = "/contractor-type/{id}", consumes = "application/json; charset=UTF-8")
    public void updateContractorType(@PathVariable Long id,
                                     @RequestBody ContractorTypePutRequest request) {
        contractorTypesService.update(new ContractorTypeData(id, request.getName(), request.getModifyUserId()));
    }

    @PutMapping(path = "/contractor-type-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeContractorType(@PathVariable Long id,
                                     @RequestBody ContractorTypePutRequest request) {
        contractorTypesService.remove(new ContractorTypeData(id, request.getRemoveUserId()));
    }


    private List<ContractorTypeGetResponse> contractorTypesToResponse(List<ContractorTypeData> contractorTypes) {
        List<ContractorTypeGetResponse> list = new ArrayList<>();
        for (ContractorTypeData data : contractorTypes) {
            list.add(contractorTypeToResponse(data));
        }
        return list;
    }

    private ContractorTypeGetResponse contractorTypeToResponse(ContractorTypeData contractorType) {
        return new ContractorTypeGetResponse(
                contractorType.getId(),
                contractorType.getName(),
                DateUtil.parseDateTime(contractorType.getCreateDate()),
                DateUtil.parseDateTime(contractorType.getModifyDate()),
                DateUtil.parseDateTime(contractorType.getRemoveDate()),
                contractorType.getCreateUserId(),
                contractorType.getModifyUserId(),
                contractorType.getRemoveUserId()
        );
    }
}
