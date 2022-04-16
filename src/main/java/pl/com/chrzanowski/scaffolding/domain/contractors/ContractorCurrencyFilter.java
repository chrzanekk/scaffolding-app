package pl.com.chrzanowski.scaffolding.domain.contractors;

public class ContractorCurrencyFilter {

    private Long contractorId;
    private Long currencyId;

    public ContractorCurrencyFilter(Long contractorId, Long currencyId) {
        this.contractorId = contractorId;
        this.currencyId = currencyId;
    }

    public ContractorCurrencyFilter(Long contractorId) {
        this.contractorId = contractorId;
    }

    public Long getContractorId() {
        return contractorId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }
}
