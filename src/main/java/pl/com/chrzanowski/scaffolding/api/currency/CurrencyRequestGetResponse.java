package pl.com.chrzanowski.scaffolding.api.currency;

public class CurrencyRequestGetResponse {

    private CurrencyGetResponse currency;

    public CurrencyRequestGetResponse(CurrencyGetResponse currency) {
        this.currency = currency;
    }

    public CurrencyGetResponse getCurrency() {
        return currency;
    }
}
