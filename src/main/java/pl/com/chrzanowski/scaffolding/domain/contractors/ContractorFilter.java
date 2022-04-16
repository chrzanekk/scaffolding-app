package pl.com.chrzanowski.scaffolding.domain.contractors;

import pl.com.chrzanowski.scaffolding.domain.CoreFieldsFilter;

public class ContractorFilter extends CoreFieldsFilter {

    private String contractorType;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private String country;
    private String bankAccount;
    private Boolean itContainsRemoveDate;

    public ContractorFilter(Long id,
                            String name,
                            Long page,
                            Long pageSize,
                            Boolean isRemoved,
                            String contractorType,
                            String taxNumber,
                            String street,
                            String buildingNo,
                            String apartmentNo,
                            String postalCode,
                            String city,
                            String country,
                            String bankAccount) {
        super(id, name, page, pageSize, isRemoved);
        this.contractorType = contractorType;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.bankAccount = bankAccount;
    }

    public ContractorFilter(Long page, Long pageSize, Boolean isRemoved) {
        super(page, pageSize, isRemoved);
    }

    public ContractorFilter(Long id) {
        super(id);
    }

    public ContractorFilter(Long id, Boolean isRemoved) {
        super(id, isRemoved);
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

    public Boolean getItContainsRemoveDate() {
        return itContainsRemoveDate;
    }
}
