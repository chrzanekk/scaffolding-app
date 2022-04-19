package pl.com.chrzanowski.scaffolding.api.contractors;

public class ContractorCurrencyGetResponse {

    private Long id;
    private Long contractorId;
    private Long currencyId;
    private String currencyName;
    private String currencyCode;

    public ContractorCurrencyGetResponse(Long id, Long contractorId, Long currencyId, String currencyName, String currencyCode) {
        this.id = id;
        this.contractorId = contractorId;
        this.currencyId = currencyId;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public Long getId() {
        return id;
    }

    public Long getContractorId() {
        return contractorId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
