package pl.com.chrzanowski.scaffolding.api;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.currency.*;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyData;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyFilter;
import pl.com.chrzanowski.scaffolding.logic.ICurrency;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminCurrency {

    private ICurrency currency;

    public ScaffoldingEndpointAdminCurrency(ICurrency currency) {
        this.currency = currency;
    }

    @GetMapping(path = "/get-currency", produces = "application/json; charset=UTF-8")
    public CurrencyListRequestGetResponse currencyList(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<CurrencyData> currencyList = currency.find(new CurrencyFilter(page, pageSize, false));
        return new CurrencyListRequestGetResponse(currencyListToResponse(currencyList));
    }

    @GetMapping(path = "/removed-currency", produces = "application/json; charset=UTF-8")
    public CurrencyListRequestGetResponse removedCurrencyList(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<CurrencyData> currencyList = currency.find(new CurrencyFilter(page, pageSize, true));
        return new CurrencyListRequestGetResponse(currencyListToResponse(currencyList));
    }

    @GetMapping(path = "/currency/{id}", produces = "application/json; charset=UTF-8")
    public CurrencyRequestGetResponse currencyById(
            @PathVariable Long id) {
        CurrencyData data = currency.find(new CurrencyFilter(id)).get(0);
        return new CurrencyRequestGetResponse(currencyToResponse(data));
    }

    @PostMapping(path = "/currency", consumes = "application/json; charset=UTF-8")
    public void addCurrency(@RequestBody CurrencyPostRequest request) {
        currency.create(new CurrencyData(
                request.getName(),
                request.getCreateUserId(),
                request.getCode()
                ));
    }

    @PutMapping(path = "/currency/{id}", consumes = "application/json; charset=UTF-8")
    public void updateCurrency(@PathVariable Long id,
                               @RequestBody CurrencyPutRequest request) {
        currency.update(new CurrencyData(id, request.getName(), request.getModifyUserId(),request.getCode() ));
    }

    @PutMapping(path = "/currency-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removeCurrency(@PathVariable Long id,
                               @RequestBody CurrencyPutRequest request) {
        currency.remove(new CurrencyData(id, request.getRemoveUserId(),request.getName(), request.getCode()));
    }

    private List<CurrencyGetResponse> currencyListToResponse(List<CurrencyData> currencyList) {
        List<CurrencyGetResponse> list = new ArrayList<>();
        for (CurrencyData data : currencyList) {
            list.add(currencyToResponse(data));
        }
        return list;
    }

    private CurrencyGetResponse currencyToResponse(CurrencyData data) {
        return new CurrencyGetResponse(
                data.getId(),
                data.getName(),
                DateUtil.parseDateTime(data.getCreateDate()),
                DateUtil.parseDateTime(data.getModifyDate()),
                DateUtil.parseDateTime(data.getRemoveDate()),
                data.getCreateUserId(),
                data.getModifyUserId(),
                data.getRemoveUserId(),
                data.getCode()
                );
    }
}
