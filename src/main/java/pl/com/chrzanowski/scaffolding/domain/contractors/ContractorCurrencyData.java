package pl.com.chrzanowski.scaffolding.domain.contractors;

import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyData;

public class ContractorCurrencyData {

    private Long id;
    private Long contractorId;
    private Long currencyId;
    private ContractorData contractorData;
    private CurrencyData currencyData;
    private Long[] currencyList;
    private String currencyName;
    private String currencyCode;

    public ContractorCurrencyData(Long id, Long contractorId, Long currencyId, String currencyName, String currencyCode) {
        this.id = id;
        this.contractorId = contractorId;
        this.currencyId = currencyId;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public ContractorCurrencyData(Long contractorId, Long[] currencyList) {
        this.contractorId = contractorId;
        this.currencyList = currencyList;
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

    public ContractorData getContractorData() {
        return contractorData;
    }

    public CurrencyData getCurrencyData() {
        return currencyData;
    }

    public Long[] getCurrencyList() {
        return currencyList;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
