package pl.com.chrzanowski.scaffolding.domain.contractors;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyData;

import java.time.LocalDateTime;
import java.util.List;

public class ContractorData extends CoreFieldsData {

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

    public ContractorData(Long id,
                          String name,
                          LocalDateTime createDate,
                          LocalDateTime modifyDate,
                          LocalDateTime removeDate,
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
                          Long[] currencyList) {
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
    }

    public ContractorData(Long id,
                          String name,
                          LocalDateTime createDate,
                          LocalDateTime modifyDate,
                          LocalDateTime removeDate,
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
                          String description) {
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
    }

    public ContractorData(Long id,
                          String name,
                          Long modifyUserId,
                          String contractorType,
                          String taxNumber,
                          String street,
                          String buildingNo,
                          String apartmentNo,
                          String postalCode,
                          String city,
                          String country,
                          String bankAccount,
                          String description) {
        super(id, name, modifyUserId);
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
    }

    public ContractorData(Long id,
                          String name,
                          LocalDateTime createDate,
                          Long createUserId,
                          ContractorData data) {
        super(id, name, createDate, createUserId);
        this.contractorType = data.getContractorType();
        this.taxNumber = data.getTaxNumber();
        this.street = data.getStreet();
        this.buildingNo = data.getBuildingNo();
        this.apartmentNo = data.getApartmentNo();
        this.postalCode = data.getPostalCode();
        this.city = data.getCity();
        this.country = data.getCountry();
        this.bankAccount = data.getBankAccount();
        this.description = data.getDescription();
        this.currencyList = data.getCurrencyList();
    }

    public ContractorData(ContractorData data,
                          Long[] currencyList) {
        this.contractorType = data.getContractorType();
        this.taxNumber = data.getTaxNumber();
        this.street = data.getStreet();
        this.buildingNo = data.getBuildingNo();
        this.apartmentNo = data.getApartmentNo();
        this.postalCode = data.getPostalCode();
        this.city = data.getCity();
        this.country = data.getCountry();
        this.bankAccount = data.getBankAccount();
        this.description = data.getDescription();
        this.currencyList = currencyList;
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
