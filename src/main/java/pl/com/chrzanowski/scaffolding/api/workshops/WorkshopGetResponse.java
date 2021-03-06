package pl.com.chrzanowski.scaffolding.api.workshops;

import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypeData;

import java.time.LocalDateTime;
import java.util.List;

public class WorkshopGetResponse {

    private Long id;
    private String name;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private Long[] actionTypes;
    private String removeDate;
    private List<ServiceActionTypeData> serviceActionTypes;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public WorkshopGetResponse(Long id,
                               String name,
                               String taxNumber,
                               String street,
                               String buildingNo,
                               String apartmentNo,
                               String postalCode,
                               String city,
                               Long[] actionTypes,
                               List<ServiceActionTypeData> serviceActionTypes,
                               String removeDate) {
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
        this.removeDate = removeDate.toString().replace('T',' ');
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

    public String getRemoveDate() {
        return removeDate;
    }

    public Long[] getActionTypes() {
        return actionTypes;
    }

    public List<ServiceActionTypeData> getServiceActionTypes() {
        return serviceActionTypes;
    }


}
