package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.contractors.*;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorCurrencyData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorCurrencyFilter;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorData;
import pl.com.chrzanowski.scaffolding.domain.contractors.ContractorFilter;
import pl.com.chrzanowski.scaffolding.domain.users.UserData;
import pl.com.chrzanowski.scaffolding.logic.IContractor;
import pl.com.chrzanowski.scaffolding.logic.contractors.ContractorCurrencyService;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminContractors {

    private IContractor contractorService;
    private ContractorCurrencyService contractorCurrencyService;
    private UserService userService;

    public ScaffoldingEndpointAdminContractors(IContractor contractorService, ContractorCurrencyService contractorCurrencyService, UserService userService) {
        this.contractorService = contractorService;
        this.contractorCurrencyService = contractorCurrencyService;
        this.userService = userService;
    }

    @GetMapping(path = "/contractors", produces = "application/json; charset=UTF-8")
    public ContractorsRequestGetResponse contractors(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "postal_code", required = false) String postalCode,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "tax_number", required = false) String taxNumber,
            @RequestParam(name = "contractor_type", required = false) String contractorType,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ContractorData> contractors = contractorService.find(new ContractorFilter(
                name,
                page,
                pageSize,
                false,
                contractorType,
                taxNumber,
                street,
                postalCode,
                city,
                country
        ));
        return new ContractorsRequestGetResponse(contractorsToResponse(contractors));
    }

    @GetMapping(path = "/removed-contractors", produces = "application/json; charset=UTF-8")
    public ContractorsRequestGetResponse removedContractors(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "postal_code", required = false) String postalCode,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "tax_number", required = false) String taxNumber,
            @RequestParam(name = "contractor_type", required = false) String contractorType,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<ContractorData> contractors = contractorService.find(new ContractorFilter(
                name,
                page,
                pageSize,
                true,
                contractorType,
                taxNumber,
                street,
                postalCode,
                city,
                country
        ));
        return new ContractorsRequestGetResponse(contractorsToResponse(contractors));
    }

    @GetMapping(path = "/contractor/{id}", produces = "application/json; charset=UTF-8")
    public ContractorRequestGetResponse contractorById(@PathVariable Long id) {
        ContractorData contractor =
                contractorService.findWithCurrency(contractorService.find(new ContractorFilter(id))).get(0);
        return new ContractorRequestGetResponse(contractorToResponse(contractor));
    }

    @PostMapping(path= "/contractor", consumes = "application/json; charset=UTF-8")
    public void addContractor(@RequestBody ContractorPostRequest request) {
        contractorService.create(new ContractorData(
                request.getName(),
                request.getCreateUserId(),
                request.getContractorType(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getCountry(),
                request.getBankAccount(),
                request.getDescription(),
                request.getCurrencyList()));
    }

    @PutMapping(path = "/contractor/{id}", consumes = "application/json; charset=UTF-8")
    public void updateContractor(@PathVariable Long id,
                                 @RequestBody ContractorPutRequest request) {
        contractorService.update(new ContractorData(
                id,
                request.getName(),
                request.getModifyUserId(),
                request.getContractorType(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getCountry(),
                request.getBankAccount(),
                request.getDescription(),
                request.getCurrencyList()));
    }

    @PutMapping(path = "/contractor-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeContractor(@PathVariable Long id,
                                 @RequestBody ContractorPutRequest request) {
        contractorService.update(new ContractorData(
                id,
                request.getRemoveUserId(),
                request.getName(),
                request.getContractorType(),
                request.getTaxNumber(),
                request.getStreet(),
                request.getBuildingNo(),
                request.getApartmentNo(),
                request.getPostalCode(),
                request.getCity(),
                request.getCountry(),
                request.getBankAccount(),
                request.getDescription(),
                request.getCurrencyList()));
    }

    @GetMapping(path = "/contractor-currency", produces = "application/json; charset=UTF-8")
    public ContractorCurrencyListRequestGetResponse currencyList(@RequestParam(name = "contractor_id") Long contractorId) {
        List<ContractorCurrencyData> contractorCurrencyList =
                contractorCurrencyService.find(new ContractorCurrencyFilter(contractorId));
        return new ContractorCurrencyListRequestGetResponse(contractorCurrencyListToResponse(contractorCurrencyList));
    }

    private List<ContractorCurrencyGetResponse> contractorCurrencyListToResponse(List<ContractorCurrencyData> contractorCurrencyList) {
        List<ContractorCurrencyGetResponse> list = new ArrayList<>();
        for(ContractorCurrencyData data : contractorCurrencyList) {
            list.add(new ContractorCurrencyGetResponse(
                    data.getId(),
                    data.getContractorId(),
                    data.getCurrencyId(),
                    data.getCurrencyName(),
                    data.getCurrencyCode()
            ));
        }
        return list;
    }


    private List<ContractorGetResponse> contractorsToResponse(List<ContractorData> contractors) {
        List<ContractorGetResponse> list = new ArrayList<>();
        for (ContractorData data : contractors) {
            list.add(contractorToResponse(data));
        }
        return list;
    }

    private ContractorGetResponse contractorToResponse(ContractorData contractor) {
        return new ContractorGetResponse(
                contractor.getId(),
                contractor.getName(),
                DateUtil.parseDateTime(contractor.getCreateDate()),
                DateUtil.parseDateTime(contractor.getModifyDate()),
                DateUtil.parseDateTime(contractor.getRemoveDate()),
                contractor.getCreateUserId(),
                contractor.getModifyUserId(),
                contractor.getRemoveUserId(),
                userService.getAndPrepareUserFullName(contractor.getCreateUserId()),
                userService.getAndPrepareUserFullName(contractor.getModifyUserId()),
                userService.getAndPrepareUserFullName(contractor.getRemoveUserId()),
                contractor.getContractorType(),
                contractor.getTaxNumber(),
                contractor.getStreet(),
                contractor.getBuildingNo(),
                contractor.getApartmentNo(),
                contractor.getPostalCode(),
                contractor.getCity(),
                contractor.getCountry(),
                contractor.getBankAccount(),
                contractor.getDescription(),
                contractor.getCurrencyList(),
                contractor.getCurrencyDataList()
                );
    }
}
