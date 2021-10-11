package pl.com.chrzanowski.scaffolding.domain;

import java.time.LocalDateTime;

public class TireSeasonData {
    private Long id;
    private String name;
    private LocalDateTime modifyDate;
    private LocalDateTime removeDate;

    public TireSeasonData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TireSeasonData(String name) {
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
