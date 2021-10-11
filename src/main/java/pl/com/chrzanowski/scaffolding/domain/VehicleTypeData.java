package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class VehicleTypeData {

    private Long id;
    private String name;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public VehicleTypeData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VehicleTypeData(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }
}
