package pl.com.chrzanowski.scaffolding.api;


import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.costaccounts.*;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountData;
import pl.com.chrzanowski.scaffolding.domain.costaccounts.CostAccountFilter;
import pl.com.chrzanowski.scaffolding.logic.ICostAccounts;
import pl.com.chrzanowski.scaffolding.logic.user.UserService;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminCostAccounts {

    private ICostAccounts costAccountsService;
    private UserService userService;

    public ScaffoldingEndpointAdminCostAccounts(ICostAccounts costAccountsService, UserService userService) {
        this.costAccountsService = costAccountsService;
        this.userService = userService;
    }

    @GetMapping(path = "/cost-accounts", produces = "application/json; charset=UTF-8")
    public CostAccountsRequestGetResponse costAccounts(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<CostAccountData> costAccounts = costAccountsService.find(new CostAccountFilter(page, pageSize, false));
        return new CostAccountsRequestGetResponse(costAccountsToResponse(costAccounts));
    }

    @GetMapping(path = "/removed-cost-accounts", produces = "application/json; charset=UTF-8")
    public CostAccountsRequestGetResponse removedCostAccounts(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<CostAccountData> costAccounts = costAccountsService.find(new CostAccountFilter(page, pageSize, true));
        return new CostAccountsRequestGetResponse(costAccountsToResponse(costAccounts));
    }

    @GetMapping(path = "/cost-account/{id}", produces = "application/json; charset=UTF-8")
    public CostAccountRequestGetResponse costAccountById(@PathVariable Long id) {
        CostAccountData costAccount = costAccountsService.find(new CostAccountFilter(id)).get(0);
        return new CostAccountRequestGetResponse(costAccountToResponse(costAccount));
    }

    @PostMapping(path = "/cost-account", consumes = "application/json; charset=UTF-8")
    public void addCostAccount(@RequestBody CostAccountPostRequest request) {
        costAccountsService.add(new CostAccountData(request.getName(), request.getCreateUserId()));
    }

    @PutMapping(path = "/cost-account/{id}", consumes = "application/json; charset=UTF-8")
    public void updateCostAccount(@PathVariable Long id,
                                  @RequestBody CostAccountPutRequest request) {
        costAccountsService.update(new CostAccountData(id, request.getName(), request.getModifyUserId()));
    }

    @PutMapping(path = "/cost-account-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeCostAccount(@PathVariable Long id,
                                  @RequestBody CostAccountPutRequest request) {
        costAccountsService.remove(new CostAccountData(id, request.getRemoveUserId(), request.getName()));
    }


    private List<CostAccountGetResponse> costAccountsToResponse(List<CostAccountData> costAccounts) {
        List<CostAccountGetResponse> list = new ArrayList<>();
        for (CostAccountData data : costAccounts) {
            list.add(costAccountToResponse(data));
        }
        return list;
    }


    private CostAccountGetResponse costAccountToResponse(CostAccountData costAccount) {
        return new CostAccountGetResponse(
                costAccount.getId(),
                costAccount.getName(),
                DateUtil.parseDateTime(costAccount.getCreateDate()),
                DateUtil.parseDateTime(costAccount.getModifyDate()),
                DateUtil.parseDateTime(costAccount.getRemoveDate()),
                costAccount.getCreateUserId(),
                costAccount.getModifyUserId(),
                costAccount.getModifyUserId(),
                userService.getAndPrepareUserFullName(costAccount.getCreateUserId()),
                userService.getAndPrepareUserFullName(costAccount.getModifyUserId()),
                userService.getAndPrepareUserFullName(costAccount.getRemoveUserId())
        );
    }
}
