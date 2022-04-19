package pl.com.chrzanowski.scaffolding.api.contractors;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsResponseRequestData;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyData;

import java.util.List;

public class ContractorGetResponse extends CoreFieldsResponseRequestData {

    private String contractorType;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private String country;
    private String bankAccount;
    private String description;
    private Long[] currencyList;
    private List<CurrencyData> currencyDataList;

    public ContractorGetResponse(Long id,
                                 String name,
                                 String createDate,
                                 String modifyDate,
                                 String removeDate,
                                 Long createUserId,
                                 Long modifyUserId,
                                 Long removeUserId,
                                 String contractorType,
                                 String taxNumber,
                                 String street,
                                 String buildingNo,
                                 String apartmentNo,
                                 String postalCode,
                                 String city,
                                 String country,
                                 String bankAccount,
                                 String description,
                                 Long[] currencyList,
                                 List<CurrencyData> currencyDataList) {
        super(id, name, createDate, modifyDate, removeDate, createUserId, modifyUserId, removeUserId);
        this.contractorType = contractorType;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.bankAccount = bankAccount;
        this.description = description;
        this.currencyList = currencyList;
        this.currencyDataList = currencyDataList;
    }

    public String getContractorType() {
        return contractorType;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getDescription() {
        return description;
    }

    public Long[] getCurrencyList() {
        return currencyList;
    }

    public List<CurrencyData> getCurrencyDataList() {
        return currencyDataList;
    }
}
