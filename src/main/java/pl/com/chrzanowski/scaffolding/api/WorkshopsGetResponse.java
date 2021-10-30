package pl.com.chrzanowski.scaffolding.api;

import pl.com.chrzanowski.scaffolding.domain.ServiceActionTypeData;
import pl.com.chrzanowski.scaffolding.domain.WorkshopsData;

import java.time.LocalDateTime;
import java.util.List;

public class WorkshopsGetResponse {

    private Long id;
    private String name;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private Long[] actionTypes;
    private List<ServiceActionTypeData> serviceActionTypes;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public WorkshopsGetResponse(Long id,
                                String name,
                                String taxNumber,
                                String street,
                                String buildingNo,
                                String apartmentNo,
                                String postalCode,
                                String city,
                                Long[] actionTypes,
                                List<ServiceActionTypeData> serviceActionTypes) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.actionTypes = actionTypes;
        this.serviceActionTypes = serviceActionTypes;
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

    public Long[] getActionTypes() {
        return actionTypes;
    }

    public List<ServiceActionTypeData> getServiceActionTypes() {
        return serviceActionTypes;
    }
}
