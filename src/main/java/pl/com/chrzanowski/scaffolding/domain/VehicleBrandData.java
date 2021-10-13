package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class VehicleBrandData {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleBrandData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VehicleBrandData(String name) {
        this.name = name;
    }

    public VehicleBrandData(Long id, String name, LocalDateTime modifyDate) {
        this.id = id;
        this.name = name;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
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
