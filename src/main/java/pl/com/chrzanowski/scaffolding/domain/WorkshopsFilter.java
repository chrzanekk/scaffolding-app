package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class WorkshopsFilter {

    private Long id;
    private String name;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private Long[] actionTypes;
    private Long page;
    private Long pageSize;

    public WorkshopsFilter(Long page, Long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public WorkshopsFilter() {
    }

    public WorkshopsFilter(Long id) {
        this.id = id;
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

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
