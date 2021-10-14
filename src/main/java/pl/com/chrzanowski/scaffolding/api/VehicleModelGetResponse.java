package pl.com.chrzanowski.scaffolding.api;


import java.time.LocalDateTime;

public class VehicleModelGetResponse {

    private Long id;
    private Long brandId;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;


    public VehicleModelGetResponse(Long id, Long brandId, String name) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getName() {
        return name;
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
