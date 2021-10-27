package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class WorkshopsData {

    private Long id;
    private String name;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private Long[] actionTypes;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;


    public WorkshopsData(Long id,
                         String name,
                         String taxNumber,
                         String street,
                         String buildingNo,
                         String apartmentNo,
                         String postalCode,
                         String city,
                         Long[] actionTypes) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.actionTypes = actionTypes;
        this.modifyDate = LocalDateTime.now();
    }

    public WorkshopsData(String name, String taxNumber, String street, String buildingNo, String apartmentNo, String postalCode, String city) {
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.createDate = LocalDateTime.now();
    }

    public WorkshopsData(WorkshopsData data, Long[] actionTypes) {
        this.id = data.getId();
        this.name = data.getName();
        this.taxNumber = data.getTaxNumber();
        this.street = data.getStreet();
        this.buildingNo = data.getBuildingNo();
        this.apartmentNo = data.getApartmentNo();
        this.postalCode = data.getPostalCode();
        this.city = data.getCity();
        this.actionTypes = actionTypes;
    }

    public WorkshopsData(Long id, String name, String taxNumber, String street, String buildingNo, String apartmentNo, String postalCode, String city) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
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

    public Long[] getActionTypes() {
        return actionTypes;
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
