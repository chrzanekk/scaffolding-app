package pl.com.chrzanowski.scaffolding.domain.serviceactiontypes;

import java.time.LocalDateTime;

public class ServiceActionTypeData {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ServiceActionTypeData(Long id, String name) {
        this.id = id;
        this.name = name;
        this.modifyDate = LocalDateTime.now();
    }

    public ServiceActionTypeData(Long id, String name, LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.modifyDate = LocalDateTime.now();
        this.removeDate = removeDate;
    }

    public ServiceActionTypeData(String name) {
        this.name = name;
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
