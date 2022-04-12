package pl.com.chrzanowski.scaffolding.api.serviceactiontypes;

import java.time.LocalDateTime;

public class ServiceActionTypeGetResponse {

    private Long id;
    private String name;
    private String createDate;
    private String modifyDate;
    private String removeDate;

    public ServiceActionTypeGetResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }
}
