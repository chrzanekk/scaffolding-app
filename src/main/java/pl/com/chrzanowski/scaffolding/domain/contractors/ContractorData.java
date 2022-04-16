package pl.com.chrzanowski.scaffolding.domain.contractors;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsData;

import java.time.LocalDateTime;

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

    public ContractorData(String name,
                          Long createUserId,
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
        super(name, createUserId);
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

    public ContractorData(Long id, Long removeUserId) {
        super(id, removeUserId);
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
}
