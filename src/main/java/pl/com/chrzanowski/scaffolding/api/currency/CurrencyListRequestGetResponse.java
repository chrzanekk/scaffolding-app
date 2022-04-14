package pl.com.chrzanowski.scaffolding.api.currency;

import java.util.List;

public class CurrencyListRequestGetResponse {

    List<CurrencyGetResponse> currencyList;

    public CurrencyListRequestGetResponse(List<CurrencyGetResponse> currencyList) {
        this.currencyList = currencyList;
    }

    public List<CurrencyGetResponse> getCurrencyList() {
        return currencyList;
    }
}
