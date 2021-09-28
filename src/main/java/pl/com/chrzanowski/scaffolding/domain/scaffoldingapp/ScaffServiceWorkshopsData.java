package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import java.time.LocalDateTime;

public class ScaffServiceWorkshopsData {

    private Long id;
    private String name;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ScaffServiceWorkshopsData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScaffServiceWorkshopsData(Long id,
                                     String name,
                                     String taxNumber,
                                     String street,
                                     String buildingNo,
                                     String apartmentNo,
                                     String postalCode,
                                     String city) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
    }

    public ScaffServiceWorkshopsData(String name, String taxNumber, String street, String buildingNo, String apartmentNo, String postalCode, String city) {
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.createDate = LocalDateTime.now();
    }

    public ScaffServiceWorkshopsData() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }
}
