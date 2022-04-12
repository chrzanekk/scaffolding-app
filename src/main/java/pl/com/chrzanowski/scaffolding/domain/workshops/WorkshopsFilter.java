package pl.com.chrzanowski.scaffolding.domain.workshops;

import pl.com.chrzanowski.scaffolding.domain.serviceactiontypes.ServiceActionTypeData;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class WorkshopsFilter {

    private Long id;
    private String name;
    private String taxNumber;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;
    private String city;
    private List<ServiceActionTypeData> actionTypesList;
    private Long[] actionTypes;
    private Long page;
    private Long pageSize;
    private LocalDateTime removeDate;
    private Boolean itContainsRemoveDate;




    public WorkshopsFilter(String name, String city, Long page, Long pageSize, @NotNull Boolean itContainsRemoveDate) {
        this.name = name;
        this.city = city;
        this.page = page;
        this.pageSize = pageSize;
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public WorkshopsFilter(@NotNull Boolean itContainsRemoveDate) {
        this.itContainsRemoveDate = itContainsRemoveDate;
    }

    public WorkshopsFilter(Long id,@NotNull Boolean itContainsRemoveDate) {
        this.id = id;
        this.itContainsRemoveDate = itContainsRemoveDate;
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

    public List<ServiceActionTypeData> getActionTypesList() {
        return actionTypesList;
    }

    public Long[] getActionTypes() {
        return actionTypes;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }

    public Boolean getItContainsRemoveDate() {
        return itContainsRemoveDate;
    }
}
