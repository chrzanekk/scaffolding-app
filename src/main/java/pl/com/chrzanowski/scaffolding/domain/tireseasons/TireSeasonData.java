package pl.com.chrzanowski.scaffolding.domain.tireseasons;

import java.time.LocalDateTime;

public class TireSeasonData {
    private Long id;
    private String name;
    private LocalDateTime createDate;
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
