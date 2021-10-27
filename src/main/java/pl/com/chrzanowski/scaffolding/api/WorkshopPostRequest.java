package pl.com.chrzanowski.scaffolding.api;

import java.time.LocalDateTime;

public class WorkshopPostRequest {

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

    public WorkshopPostRequest() {
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
