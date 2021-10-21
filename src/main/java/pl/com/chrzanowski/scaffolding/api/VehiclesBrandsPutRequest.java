package pl.com.chrzanowski.scaffolding.api;

import java.time.LocalDateTime;

public class VehiclesBrandsPutRequest {

    private Long brandId;
    private String brandName;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehiclesBrandsPutRequest() {
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
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