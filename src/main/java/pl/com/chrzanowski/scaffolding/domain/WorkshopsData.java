package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<ServiceActionTypeData> actionTypesList;
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

    public WorkshopsData(Long id,
                         String name,
                         String taxNumber,
                         String street,
                         String buildingNo,
                         String apartmentNo,
                         String postalCode,
                         String city,
                         Long[] actionTypes,
                         LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.actionTypes = actionTypes;
        this.removeDate = removeDate;
    }

    public WorkshopsData(String name, String taxNumber, String street, String buildingNo, String apartmentNo,
                         String postalCode, String city, Long[] actionTypes) {
        this.name = name;
        this.taxNumber = taxNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postalCode = postalCode;
        this.city = city;
        this.actionTypes = actionTypes;
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
        this.modifyDate = LocalDateTime.now();
    }

    public WorkshopsData(WorkshopsData data, Long[] actionTypes, List<ServiceActionTypeData> actionTypesList) {
        this.id = data.getId();
        this.name = data.getName();
        this.taxNumber = data.getTaxNumber();
        this.street = data.getStreet();
        this.buildingNo = data.getBuildingNo();
        this.apartmentNo = data.getApartmentNo();
        this.postalCode = data.getPostalCode();
        this.city = data.getCity();
        this.actionTypes = actionTypes;
        this.actionTypesList = actionTypesList;
        this.modifyDate = LocalDateTime.now();
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

    public WorkshopsData(Long id, WorkshopsData data) {
        this.id = id;
        this.name = data.getName();
        this.taxNumber = data.getTaxNumber();
        this.street = data.getStreet();
        this.buildingNo = data.getBuildingNo();
        this.apartmentNo = data.getApartmentNo();
        this.postalCode = data.getPostalCode();
        this.city = data.getCity();
        this.actionTypes = data.getActionTypes();
        this.createDate = LocalDateTime.now();
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

    public List<ServiceActionTypeData> getActionTypesList() {
        return actionTypesList;
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
