package pl.com.chrzanowski.scaffolding.domain.scaffoldingapp;

import java.time.LocalDateTime;

public class ScaffServiceActionTypeData {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public ScaffServiceActionTypeData(Long id, String name) {
        this.id = id;
        this.name = name;
        this.modifyDate = LocalDateTime.now();
    }

    public ScaffServiceActionTypeData(String name) {
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
