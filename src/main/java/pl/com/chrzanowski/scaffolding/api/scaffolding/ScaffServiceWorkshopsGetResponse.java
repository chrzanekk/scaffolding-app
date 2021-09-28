package pl.com.chrzanowski.scaffolding.api.scaffolding;

import pl.com.chrzanowski.scaffolding.domain.scaffoldingapp.ScaffServiceWorkshopsData;

import java.time.LocalDateTime;

public class ScaffServiceWorkshopsGetResponse {

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

    public ScaffServiceWorkshopsGetResponse(Long id,
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

    public ScaffServiceWorkshopsGetResponse(ScaffServiceWorkshopsData data) {
        this.id = data.getId();
        this.name = data.getName();
        this.taxNumber = data.getTaxNumber();
        this.street = data.getStreet();
        this.buildingNo = data.getBuildingNo();
        this.apartmentNo = data.getApartmentNo();
        this.postalCode = data.getPostalCode();
        this.city = data.getCity();
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
